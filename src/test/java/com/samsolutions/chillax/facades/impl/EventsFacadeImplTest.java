package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.converters.EventsConverterFromDTO;
import com.samsolutions.chillax.converters.EventsConverterToDTO;
import com.samsolutions.chillax.dto.EventsDTO;
import com.samsolutions.chillax.dto.TypesDTO;
import com.samsolutions.chillax.entity.Events;
import com.samsolutions.chillax.entity.Types;
import com.samsolutions.chillax.services.EventsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EventsFacadeImplTest
{

    @Mock
    private EventsService eventService;

    @Mock
    private EventsConverterToDTO converterToDTO;

    @Mock
    private EventsConverterFromDTO converterFromDTO;

    private static List<Events> eventsList = new ArrayList<>();

    private static List<EventsDTO> eventsDTOList = new ArrayList<>();

    @InjectMocks
    private EventsFacadeImpl eventsFacade;

    @BeforeAll
    public static void setUp()
    {
        Types type = new Types(1, "Music");
        TypesDTO typeDTO = new TypesDTO(1, "Music");
        eventsDTOList.add(new EventsDTO(1, "Динамо-Минск - СКА", "Хоккейный матч Динамо-Минск - СКА", Timestamp.valueOf("2019-01-04 19:30:00.000"), 7.00, 15000, "cka.jpg", typeDTO, null));
        eventsDTOList.add(new EventsDTO(2, "Brit Floyd", "The World's Greatest Pink Floyd Tribute Show", Timestamp.valueOf("2018-11-24 20:00:00.000"), 100.00, 1500, "britFloyd.jpg", typeDTO, null));
        eventsList.add(new Events(1, "Динамо-Минск - СКА", "Хоккейный матч Динамо-Минск - СКА", "cka.jpg", Timestamp.valueOf("2019-01-04 19:30:00.000"), 7.00, 15000, type, null));
        eventsList.add(new Events(2, "Brit Floyd", "The World's Greatest Pink Floyd Tribute Show", "britFloyd.jpg", Timestamp.valueOf("2018-11-24 20:00:00.000"), 100.00, 1500, type, null));
    }
    @BeforeEach
    public void set()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getEventsDTO()
    {
        when(eventService.getEvents(1,12)).thenReturn(eventsList);
        when(converterToDTO.convert(eventsList.get(0))).thenReturn(eventsDTOList.get(0));
        when(converterToDTO.convert(eventsList.get(1))).thenReturn(eventsDTOList.get(1));
        List<EventsDTO> actList = eventsFacade.getEventsDTO(1,12);
        assertNotNull(actList);
        assertEquals(eventsDTOList, actList);
    }

    @Test
    public void getEventsDTOByType()
    {
        when(eventService.getEventsByType(1,12, 1)).thenReturn(eventsList);
        when(converterToDTO.convert(eventsList.get(0))).thenReturn(eventsDTOList.get(0));
        when(converterToDTO.convert(eventsList.get(1))).thenReturn(eventsDTOList.get(1));
        List<EventsDTO> actList = eventsFacade.getEventsDTOByType(1, 12, 1);
        assertNotNull(actList);
        assertEquals(eventsDTOList, actList);
    }

    @Test
    public void getEventByPK()
    {
        Events event = eventsList.get(0);
        EventsDTO eventsDTO = eventsDTOList.get(0);
        when(eventService.getEventByPK(1)).thenReturn(event);
        when(converterToDTO.convert(event)).thenReturn(eventsDTO);
        EventsDTO actEvent = eventsFacade.getEventByPK(1);
        assertNotNull(actEvent);
        assertEquals(eventsDTO, actEvent);
    }

    @Test
    public void getByPK()
    {
        Events event = eventsList.get(0);
        when(eventService.getEventByPK(1)).thenReturn(event);
        Events actEvent = eventsFacade.getByPK(1);
        assertNotNull(actEvent);
        assertEquals(event, actEvent);
    }

    @Test
    public void getAllEvents()
    {
        when(eventService.getAllEvents()).thenReturn(eventsList);
        when(converterToDTO.convert(eventsList.get(0))).thenReturn(eventsDTOList.get(0));
        when(converterToDTO.convert(eventsList.get(1))).thenReturn(eventsDTOList.get(1));
        List<EventsDTO> actList = eventsFacade.getAllEvents();
        assertNotNull(actList);
        assertEquals(eventsDTOList, actList);
    }

    @Test
    public void getAllEventsByType()
    {
        when(eventService.getAllEvents(1)).thenReturn(eventsList);
        when(converterToDTO.convert(eventsList.get(0))).thenReturn(eventsDTOList.get(0));
        when(converterToDTO.convert(eventsList.get(1))).thenReturn(eventsDTOList.get(1));
        List<EventsDTO> actList = eventsFacade.getAllEventsByType(1);
        assertNotNull(actList);
        assertEquals(eventsDTOList, actList);
    }

    @Test
    public void eventUpdate()
    {
        Events updatingEvent = eventsList.get(0);
        doNothing().when(eventService).eventUpdate(updatingEvent);
        when(converterFromDTO.convert(eventsDTOList.get(0))).thenReturn(updatingEvent);
        eventsFacade.eventUpdate(eventsDTOList.get(0));
        verify(eventService).eventUpdate(updatingEvent);
    }

    @Test
    public void eventDelete()
    {
        doNothing().when(eventService).eventDelete(1);
        eventsFacade.eventDelete(1);
        verify(eventService).eventDelete(1);
    }

    @Test
    public void addNewEvent()
    {
        Events newEvent = eventsList.get(0);
        doNothing().when(eventService).addEvent(newEvent);
        when(converterFromDTO.convert(eventsDTOList.get(0))).thenReturn(newEvent);
        eventsFacade.addNewEvent(eventsDTOList.get(0));
        verify(eventService).addEvent(newEvent);
    }

    @Test
    public void getOrderedEvents()
    {
        when(eventService.getOrderedEvents("some guid", "admin")).thenReturn(eventsList);
        when(converterToDTO.convert(eventsList.get(0))).thenReturn(eventsDTOList.get(0));
        when(converterToDTO.convert(eventsList.get(1))).thenReturn(eventsDTOList.get(1));
        List<EventsDTO> actList = eventsFacade.getOrderedEvents("some guid", "admin");
        assertNotNull(actList);
        assertEquals(eventsDTOList, actList);
    }

    @Test
    public void getOrderedEventsToWrongUser()
    {
        when(eventService.getOrderedEvents("some guid", "admin")).thenReturn(eventsList);
        when(converterToDTO.convert(eventsList.get(0))).thenReturn(eventsDTOList.get(0));
        when(converterToDTO.convert(eventsList.get(1))).thenReturn(eventsDTOList.get(1));
        List<EventsDTO> actList = eventsFacade.getOrderedEvents("some guid", "user");
        assertTrue(actList.isEmpty());
        assertNotEquals(eventsDTOList, actList);
    }
}