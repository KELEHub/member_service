package com.member.services.front;

import java.util.List;

import com.member.entity.Information;

public interface RecommendRelationService {

	List<Information> getRecommendRelation(String recommendNumber);

}
