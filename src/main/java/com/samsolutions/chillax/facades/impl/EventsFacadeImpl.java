package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.converters.EventsConverterFromDTO;
import com.samsolutions.chillax.converters.EventsConverterToDTO;
import com.samsolutions.chillax.dto.EventsDTO;
import com.samsolutions.chillax.entity.Events;
import com.samsolutions.chillax.facades.EventsFacade;
import com.samsolutions.chillax.services.EventsService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@Component
public class EventsFacadeImpl implements EventsFacade {

    @Autowired
    private EventsService eventService;

    @Autowired
    private EventsConverterToDTO converterToDTO;

    @Autowired
    private EventsConverterFromDTO converterFromDTO;

    private static final Logger logger = getLogger(EventsFacadeImpl.class);

    @Value("${media.path}")
    private String directory;

    public List<EventsDTO> getEventsDTO(int page, int pageSize)
    {
        List<Events> list = eventService.getEvents(page, pageSize);
        List<EventsDTO> eventsDTO = new ArrayList<EventsDTO>();
        for (Events event : list)
        {
            eventsDTO.add(converterToDTO.convert(event));
        }
        return eventsDTO;
    }

    public List<EventsDTO> getEventsDTOByType(int page, int pageSize, int type)
    {
        List<Events> list = eventService.getEventsByType(page, pageSize, type);
        List<EventsDTO> eventsDTO = new ArrayList<EventsDTO>();
        for (Events event : list)
        {
            eventsDTO.add(converterToDTO.convert(event));
        }
        return eventsDTO;
    }

    public EventsDTO getEventByPK(int id)
    {
        return converterToDTO.convert(eventService.getEventByPK(id));
    }

    public Events getByPK(int id)
    {
        return eventService.getEventByPK(id);
    }

    public List<EventsDTO> getAllEvents()
    {
        List<Events> list = eventService.getAllEvents();
        List<EventsDTO> eventsDTO = new ArrayList<EventsDTO>();
        for (Events event : list)
        {
            eventsDTO.add(converterToDTO.convert(event));
        }
        return eventsDTO;
    }

    public List<EventsDTO> getAllEventsByType(int type)
    {
        List<Events> list = eventService.getAllEvents(type);
        List<EventsDTO> eventsDTO = new ArrayList<EventsDTO>();
        for (Events event : list)
        {
            eventsDTO.add(converterToDTO.convert(event));
        }
        return eventsDTO;
    }

    public void eventUpdate(EventsDTO eventsDTO)
    {
        eventService.eventUpdate(converterFromDTO.convert(eventsDTO));
    }

    public void eventDelete(int id)
    {
        eventService.eventDelete(id);
    }

    public void addNewEvent(EventsDTO eventsDTO)
    {
        if (eventsDTO.getPoster() == null)
        {
            eventsDTO.setPoster("default.jpg");
        }
        eventService.addEvent(converterFromDTO.convert(eventsDTO));
    }

    public List<EventsDTO> getOrderedEvents(String orderGuid, String login)
    {
        List<EventsDTO> eventsDTOS = new ArrayList<>();
        List<Events> list = eventService.getOrderedEvents(orderGuid, login);
        if (!list.isEmpty())
        {
            for (Events event : list)
            {
                eventsDTOS.add(converterToDTO.convert(event));
            }
        }
        return eventsDTOS;
    }

    public boolean loadImage(MultipartFile image, HttpServletRequest request) {
        boolean posterIsUnique = eventService.checkUniqueImage(image.getOriginalFilename());
        if (posterIsUnique) {
            try {
                String filePath = request.getServletContext().getRealPath(directory);
                image.transferTo(new File(filePath + image.getOriginalFilename()));
            } catch (IOException e) {
                logger.error("Poster isn`t found", e);
            }
        }
        return posterIsUnique;
    }
}