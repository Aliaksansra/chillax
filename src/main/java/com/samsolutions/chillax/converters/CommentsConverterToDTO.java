package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.CommentsDTO;
import com.samsolutions.chillax.entity.Comments;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming a comments entities into a DTO.
 *
 * @see Converter
 * @see Comments
 * @see CommentsDTO
 */
@Component
public class CommentsConverterToDTO implements Converter<Comments, CommentsDTO>
{
    /**
     * Overrides method 'convert', converts the @param Comments
     * to the CommentsDTO and returns it.
     *
     * @param comment Comments
     * @return CommentsDTO
     */
    @Override
    public CommentsDTO convert(Comments comment)
    {
        CommentsDTO commentsDTO = null;
        if (comment != null)
        {
            commentsDTO = new CommentsDTO(comment.getIdComments(), comment.getComment(),
                    comment.getDateOfComment(), comment.getEvent().getIdEvent(), comment.getUser().getLogin());
        }
        return commentsDTO;
    }
}