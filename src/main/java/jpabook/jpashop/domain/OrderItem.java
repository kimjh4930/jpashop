package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "orderitem_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private Long orderPrice;

    private Long count;
}
