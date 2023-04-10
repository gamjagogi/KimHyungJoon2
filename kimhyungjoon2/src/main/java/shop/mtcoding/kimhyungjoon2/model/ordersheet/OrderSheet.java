package shop.mtcoding.kimhyungjoon2.model.ordersheet;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.kimhyungjoon2.model.orderproduct.OrderProduct;
import shop.mtcoding.kimhyungjoon2.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter // 관련 DTO 만들면 삭제
@Entity
@Table(name = "order_sheet_tb")
@NoArgsConstructor
public class OrderSheet { //상품 주문서(영수증)
    // id,user객체, 총 상품 객체, 총 가격, 생성일자(장바구니), 장바구니 업데이트 일자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // 왜 int형으로 안하고 long으로할까
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany(mappedBy = "orderSheet",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderProduct> orderProductList = new ArrayList<>();

    private Integer totalPrice;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // 생성,업데이트 직전, 생성일자,업데이트일자 자동, 현재날짜로 의존성 주입되게 하기.
    @PrePersist
    protected void onCreateAt(){
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
    // 생성자 builder로 만들기.

    @Builder
    public OrderSheet(long id, User user, Integer totalPrice, LocalDateTime createdAt,LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
