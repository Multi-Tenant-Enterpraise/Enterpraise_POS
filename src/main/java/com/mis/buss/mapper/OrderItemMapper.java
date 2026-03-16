package com.mis.buss.mapper;

import com.mis.buss.model.OrderItem;
import com.mis.buss.payload.dto.OrderItemDTO;

public class OrderItemMapper {

    public static OrderItemDTO toDTO(OrderItem item) {

        if (item == null)
            return null;

        return OrderItemDTO.builder()
                .id(item.getId())
                .productId(item.getProduct().getId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .build();
    }
}