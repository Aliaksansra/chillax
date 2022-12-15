package com.samsolutions.chillax.facades.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.samsolutions.chillax.converters.TicketsConverterToDTO;
import com.samsolutions.chillax.dto.EventsDTO;
import com.samsolutions.chillax.dto.TicketsDTO;
import com.samsolutions.chillax.entity.Tickets;
import com.samsolutions.chillax.services.TicketsService;
import com.samsolutions.chillax.facades.TicketsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class TicketsFacadeImpl implements TicketsFacade
{
    @Autowired
    private TicketsService ticketsService;

    @Autowired
    private TicketsConverterToDTO converterToDTO;

    @Value("${QRCode.size}")
    private int QRCodeSizes;

    public int getRemainderOfTickets(int idEvent)
    {
        return ticketsService.getTicketsNumberByEvent(idEvent);
    }

    public List<TicketsDTO> getTicketsByOrder(String orderGuid, String login)
    {
        List<Tickets> list = ticketsService.getTicketsByOrder(orderGuid, login);
        if(!list.isEmpty())
        {
            List<TicketsDTO> listDTO = new ArrayList<>();
            for (Tickets ticket : list)
            {
                listDTO.add(converterToDTO.convert(ticket));
            }
            return listDTO;
        } else
            {
            return null;
        }
    }

    public int getCountOfTickets(String orderGuid, int idEvent, String login)
    {
        return ticketsService.getCountTickets(orderGuid, idEvent, login);
    }

    public BufferedImage createQRCode(String guid, String login) throws WriterException
    {
        TicketsDTO ticketsDTO = converterToDTO.convert(ticketsService.getTicketByGuid(guid, login));
        if(ticketsDTO != null)
        {
            EventsDTO eventsDTO = ticketsDTO.getEvent();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            String s = eventsDTO.getTitle() + ", " + dateFormat.format(eventsDTO.getDatetimeOfEvent()) + ", price: " + eventsDTO.getPrice() + " BYN, " + eventsDTO.getPlace().toString(); //+ ", " + ticketsDTO.toString() + ", " + ticketsDTO.getOrder().toString();
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            return MatrixToImageWriter.toBufferedImage(qrCodeWriter.encode(s, BarcodeFormat.QR_CODE, QRCodeSizes, QRCodeSizes, hints));
        } else
            {
            return null;
        }
    }
}