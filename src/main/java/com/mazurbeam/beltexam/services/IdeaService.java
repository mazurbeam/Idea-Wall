package com.mazurbeam.beltexam.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mazurbeam.beltexam.models.Idea;
import com.mazurbeam.beltexam.repositories.IdeaRepository;

@Service
public class IdeaService {
	private IdeaRepository ideaRepository;
	
	public IdeaService(IdeaRepository ideaRepository) {
		this.ideaRepository = ideaRepository;
	}
	
	public void saveIdea(Idea idea) {
		ideaRepository.save(idea);
	}
	
	public List<Idea> findAllIdeas(){
		return ideaRepository.findAll();
	}
	
	public Idea findIdeaWithId(Long id) {
		return ideaRepository.findOne(id);
	}
	
	public void deleteIdea(Idea idea) {
		ideaRepository.delete(idea);
	}

}
