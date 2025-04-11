package model;

import java.io.Serializable;
import java.util.List;

public record Row(String key, List<Object> fields) implements Serializable {
	//the hash code of its key exlusive-or (^) the hash code of its list of fields
	@Override
	public int hashCode() {
		return this.key.hashCode() ^ this.fields.hashCode();
	}
}