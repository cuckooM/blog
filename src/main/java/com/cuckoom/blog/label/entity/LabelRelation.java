package com.cuckoom.blog.label.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.cuckoom.blog.common.entity.ModuleEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 标签与数据的关联关系
 * @author cuckooM
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_label_relation")
public class LabelRelation implements Serializable {
    private static final long serialVersionUID = -1941688650220862722L;

    /** 自增 ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 标签 ID */
    @Column(name = "label_id")
    private Long labelId;

    /** 标签 */
    @ManyToOne
    @JoinColumn(name = "label_id", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Label label;

    /** 数据 ID */
    @Column(name = "entity_id")
    private Long entityId;

    /** 类型 */
    @Enumerated
    private ModuleEnum type;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LabelRelation that = (LabelRelation) o;
        return Objects.equals(labelId, that.labelId) && Objects.equals(entityId, that.entityId) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(labelId, entityId, type);
    }
}
