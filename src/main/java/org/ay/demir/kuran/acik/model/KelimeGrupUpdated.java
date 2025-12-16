package org.ay.demir.kuran.acik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "kelime_grup_updated", schema = "acik_kuran", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "verseNumber", "surahId", "sortNumber" }) })
public class KelimeGrupUpdated {

	@Id
	public Long id;

	public Long rootDiffId;

	public Long rootId;

	public String latin;

	public Long verseId;

	public Long surahId;

	public Long verseNumber;

	public Long sortNumber;

	@Override
	public String toString() {
		return "KelimeGrupUpdated [id=" + id + ", rootDiffId=" + rootDiffId + ", rootId=" + rootId + ", latin=" + latin
				+ ", verseId=" + verseId + ", surahId=" + surahId + ", verseNumber=" + verseNumber + ", sortNumber="
				+ sortNumber + "]";
	}

}
