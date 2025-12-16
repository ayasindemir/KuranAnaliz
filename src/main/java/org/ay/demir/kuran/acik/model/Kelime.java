package org.ay.demir.kuran.acik.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "kelime", schema = "acik_kuran", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "ayetNo", "sureId", "kelimeNo" }) })
public class Kelime implements Comparable<Kelime> {

	@Id
	@JsonIgnore
	public Long id;

	@JsonProperty("si")
	public Long sureId;

	@JsonIgnore
	public Long ayetId;

	@JsonProperty("vn")
	public Long ayetNo;

	@JsonProperty("wo")
	public String kelime;

	@JsonProperty("tr")
	public String meal;

	@JsonProperty("wn")
	public Long kelimeNo;

	@JsonIgnore
	@JsonProperty("ri")
	public Long kokId;

	@JsonProperty("ro")
	public String kok;
	
	@JsonIgnore
	@JsonProperty("wgi")
	public Long kelimeGrupId;
	
	@JsonProperty("wg")
	public String kelimeGrup;

	@Override
	public int compareTo(Kelime o) {
		int c = this.sureId.compareTo(o.sureId);
		if (c == 0) {
			c = this.ayetId.compareTo(o.ayetId);
		}
		if (c == 0) {
			c = this.kelimeNo.compareTo(o.kelimeNo);
		}
		return c;
	}

}
