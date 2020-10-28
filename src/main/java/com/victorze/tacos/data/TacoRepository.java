package com.victorze.tacos.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.victorze.tacos.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}