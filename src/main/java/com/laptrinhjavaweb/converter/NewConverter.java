package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.NewEntity;

@Component
public class NewConverter {
	
	public NewEntity toEntity(NewDTO dto) {
		NewEntity news = new NewEntity();
		news.setTitle(dto.getTitle());
		news.setContent(dto.getContent());
		news.setThumbnail(dto.getThumbnail());
		news.setShortDescription(dto.getShortDescription());
		return news;	
	}
	
	public NewEntity toEntity(NewDTO dto,NewEntity entity) {
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setThumbnail(dto.getThumbnail());
		entity.setShortDescription(dto.getShortDescription());
		return entity;	
	}
	
	public NewDTO toDTO(NewEntity entity) {
		NewDTO news = new NewDTO();
		if (entity.getId() != null) {
			news.setId(entity.getId());
		}
		news.setTitle(entity.getTitle());
		news.setContent(entity.getContent());
		news.setThumbnail(entity.getThumbnail());
		news.setShortDescription(entity.getShortDescription());
		news.setCreatedDate(entity.getCreateDate());
		news.setCreatedBy(entity.getCreateBy());
		news.setModifieddate(entity.getModifiedDate());
		news.setModifiedBy(entity.getModifiedBy());
		return news;	
	}
}
