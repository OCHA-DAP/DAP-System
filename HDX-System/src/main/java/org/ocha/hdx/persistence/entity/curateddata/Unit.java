package org.ocha.hdx.persistence.entity.curateddata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.ocha.hdx.persistence.entity.i18n.Text;

/**
 * @author Alexandru Artimon
 * @since 17/02/14
 *
 * The unit of measure for the indicator (can also be generic units like "text" for non-countable indicators)
 */
@Entity
@Table(name = "hdx_unit" )
@SequenceGenerator(name = "hdx_unit_seq", sequenceName = "hdx_unit_seq")
public class Unit {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "hdx_unit_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false, updatable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "text_id")
    @ForeignKey(name = "fk_entity_to_name_text")
    private Text name;


    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Text getName() {
        return name;
    }

    public void setName(final Text name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "Unit [id=" + id + ", code=" + code + ", name=" + name + "]";
	}

}
