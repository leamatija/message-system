package com.message;

import com.message.entity.Message;
import com.message.entity.MessageType;
import com.message.repository.MessageRepository;
import com.message.repository.MessageTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MessageSystemApplication implements CommandLineRunner {

	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private MessageTypeRepository messageTypeRepository;

	public static void main(String[] args) {
		SpringApplication.run(MessageSystemApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		messageTypeRepository.deleteAll();
		messageRepository.deleteAll();

		List<MessageType> msgTypes = new ArrayList<>();

		msgTypes.add(new MessageType("Road Closure",1,30));
		msgTypes.add(new MessageType("Accident Alert",2,30));
		msgTypes.add(new MessageType("Traffic Jam Warning",3,30));
		msgTypes.add(new MessageType("MORNING_MESSAGE",4,30));
		msgTypes.add(new MessageType("MID_DAY_MESSAGE",5,30));
		msgTypes.add(new MessageType("EVENING_MESSAGE",6,30));
		msgTypes=messageTypeRepository.saveAll(msgTypes);

		List<Message> scheduledMsg = new ArrayList<>();

		var date = LocalDateTime.now();

		scheduledMsg.add(new Message("Good morning wish you a nice trip",msgTypes.get(3),true,date));
//		scheduledMsg.add(new Message("Road is closed A ",msgTypes.get(0),false,date.plusSeconds(30)));
//		scheduledMsg.add(new Message("Road is closed B",msgTypes.get(0),false,date.plusSeconds(60)));
		scheduledMsg.add(new Message("Good afternoon with you a nice trip",msgTypes.get(4),true,date.plusSeconds(80)));
		scheduledMsg.add(new Message("Good evening wish you a nice trip",msgTypes.get(5),true,date.plusSeconds(180)));
//		scheduledMsg.add(new Message("Car Crash A ",msgTypes.get(1),false,date.plusSeconds(90)));
//		scheduledMsg.add(new Message("Car Crash B",msgTypes.get(1),false,date.plusSeconds(120)));
		messageRepository.saveAll(scheduledMsg);






	}
}
