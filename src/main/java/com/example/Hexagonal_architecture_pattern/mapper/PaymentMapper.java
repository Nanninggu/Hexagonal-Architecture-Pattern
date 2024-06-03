package com.example.Hexagonal_architecture_pattern.mapper;

import com.example.Hexagonal_architecture_pattern.dto.PaymentEvent;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PaymentMapper {
    @Insert("INSERT INTO public.payments(\"orderId\", amount, \"eventType\") VALUES(#{orderId}, #{amount}, #{eventType})")
    @Options(useGeneratedKeys = true, keyProperty = "paymentId")
    void addPayment(PaymentEvent payment);

    @Select("SELECT * FROM public.payments WHERE \"paymentId\" = #{paymentId}")
    PaymentEvent getPayment(int id);

    @Update("UPDATE public.payments SET \"orderId\" = #{orderId}, amount = #{amount}, \"eventType\" = #{eventType} WHERE \"paymentId\" = #{paymentId}")
    void updatePayment(PaymentEvent payment);

    @Delete("DELETE FROM public.payments WHERE \"paymentId\" = #{paymentId}")
    void deletePayment(int id);
}
