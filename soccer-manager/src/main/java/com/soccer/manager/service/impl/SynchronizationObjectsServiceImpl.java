package com.soccer.manager.service.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.soccer.manager.service.SynchronizationObjectsService;

import lombok.Getter;

@Getter
@Service
public class SynchronizationObjectsServiceImpl implements SynchronizationObjectsService {

	private ConcurrentHashMap<String, Object> mapOfSynchronizationObjects = new ConcurrentHashMap<>();

	@Override
	public Object get(String id) {
		mapOfSynchronizationObjects.putIfAbsent(id, new Object());
		return mapOfSynchronizationObjects.get(id);
	}	
}
