<<<<<<< HEAD:src/main/java/com/gg/gop/dto/ChatMessage.java
package com.gg.gop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
	public enum MessageType{
		ENTER,TALK,QUIT,submit,accept,denied
	}
	private MessageType type;
	private int roomId;
	private String sender;
	private String message;
}
=======
package com.gg.gop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
	public enum MessageType{
		ENTER,TALK,QUIT
	}
	private MessageType type;
	private String roomId;
	private String sender;
	private String message;
}
>>>>>>> YS:GOP.GG-main/src/main/java/com/gg/gop/dto/ChatMessage.java
