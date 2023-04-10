package shop.mtcoding.kimhyungjoon2.model.orderproduct;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.kimhyungjoon2.model.ordersheet.OrderSheet;
import shop.mtcoding.kimhyungjoon2.model.product.Product;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_product_tb")
public class OrderProduct { //장바구니
    // id, 상품, 갯수, 주문금액 , 생성,업데이트 일자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    // orderProduct : 장바구니 테이블의 모든 상품 레코드를 OrderSheet에 List로 전달.

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderSheet orderSheet;

    private Integer orderTotal;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    @Builder
    public OrderProduct(Long id, Product product,Integer orderTotal, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.product = product;
        this.orderTotal = orderTotal;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
