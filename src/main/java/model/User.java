package model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Table(name = "users")
public class User extends BaseEntity {
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String specialty;



}
