/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author davlad
 */
@Mapper
public interface EventsMapper {

    EventsMapper INSTANCE = Mappers.getMapper( EventsMapper.class );

    @Mapping(source = "id", target = "id")
    integration.events.Events eventsToEventsDto(entities.Events event);
    @Mapping(source = "id", target = "id")
    entities.Events eventsDtoToEvents(integration.events.Events event);
}