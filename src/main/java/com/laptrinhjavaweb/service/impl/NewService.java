package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.INewService;

@Service
public class NewService implements INewService{

	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private NewConverter newConverter;

	@Override
	public NewDTO save(NewDTO newDTO) {
		CategoryEntity category = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		NewEntity newEntity = new NewEntity();
		if(newDTO.getId() != null) {
			NewEntity oldnew = newRepository.findOne(newDTO.getId());
			newEntity = newConverter.toEntity(newDTO,oldnew);
		} else {
			newEntity = newConverter.toEntity(newDTO);			
		}
		newEntity.setCategory(category);
		newEntity = newRepository.save(newEntity);
		return newConverter.toDTO(newEntity);
	}

	@Override
	public void delete(long[] ids) {
		for(long id:ids) {
			newRepository.delete(id);
		}
		
	}

	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> news = new ArrayList<NewDTO>();
		List<NewEntity> entitys = newRepository.findAll(pageable).getContent();
		for(NewEntity item : entitys) {
			NewDTO newDTO = newConverter.toDTO(item);
			news.add(newDTO);
		}
		return news;
	}

	@Override
	public int totalItem() {
		// TODO Auto-generated method stub
		return (int) newRepository.count();
	}
}
