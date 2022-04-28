package uz.pdp.online.newappwarehouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.online.newappwarehouse.entity.template.AbsEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Warehouse extends AbsEntity {
}
