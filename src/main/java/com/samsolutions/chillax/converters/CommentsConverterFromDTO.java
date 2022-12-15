package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.CommentsDTO;
import com.samsolutions.chillax.entity.Comments;
import com.samsolutions.chillax.facades.EventsFacade;
import com.samsolutions.chillax.facades.UsersFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming a comments entities from a DTO.
 *
 * @see Converter
 * @see CommentsDTO
 * @see Comments
 */
@Component
public class CommentsConverterFromDTO implements Converter<CommentsDTO, Comments>
{
    /**
     * Automatic injects of the UsersFacade class
     * to get the Users entity by login required to create the Comments entity.
     *
     * @see UsersFacade
     */
    @Autowired
    private UsersFacade usersFacade;

    /**
     * Automatic injects of the EventsFacade class
     * to get the Events entity by id required to create the Comments entity.
     *
     * @see EventsFacade
     */
    @Autowired
    private EventsFacade eventsFacade;

    /**
     * Overrides method 'convert', converts the @param CommentsDTO
     * to the Comments entity and returns it.
     *
     * @param commentsDTO CommentsDTO
     * @return Comments
     */
    @Override
    public Comments convert(CommentsDTO commentsDTO)
    {
        Comments comments = null;
        if (commentsDTO != null)
        {
            comments = new Comments(commentsDTO.getIdComments(), commentsDTO.getComment(),
                    commentsDTO.getDateOfComment(), eventsFacade.getByPK(commentsDTO.getIdEvent()), usersFacade.getUserByLogin(commentsDTO.getLogin()));
        }
        return comments;
    }
}