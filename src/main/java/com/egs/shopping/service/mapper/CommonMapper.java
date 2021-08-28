package com.egs.shopping.service.mapper;

import java.util.List;

public interface CommonMapper<E, I, C, U> {

    I toDtoInfo(E e);

    List<I> toDtoInfo(List<E> eList);

    E toEntityFromCreate(C c);

    E toEntityFromUpdate(U u);
}
