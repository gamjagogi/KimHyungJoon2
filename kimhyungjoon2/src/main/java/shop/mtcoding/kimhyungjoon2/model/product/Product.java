package shop.mtcoding.kimhyungjoon2.model.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.kimhyungjoon2.model.board.Board;
import shop.mtcoding.kimhyungjoon2.model.orderproduct.OrderProduct;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table(name = "product_tb")
@Getter
@Setter
public class Product {
    // id,장바구니, 상품이름,가겨,재고, 생성,업데이트 일자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price;
    private Integer qty; //재고

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Builder
    public Product(Long id,Integer price, Integer qty, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.price = price;
        this.qty = qty;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
