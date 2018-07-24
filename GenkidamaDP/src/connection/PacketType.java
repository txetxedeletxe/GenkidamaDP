package connection;

import java.io.Serializable;

public enum PacketType implements Serializable {

	ADD_SOURCE,
	START_COMPUTATION,
	RETRANSMIT_RESULT
}
