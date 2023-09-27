package com.message;

import com.message.entity.Message;
import com.message.entity.MessageType;
import com.message.repository.MessageRepository;
import com.message.repository.MessageTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoDatabaseFactorySupport;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MessageSystemApplication implements CommandLineRunner {

	@Autowired
	MessageRepository messageRepository;
	@Autowired
	 MessageTypeRepository messageTypeRepository;

	public static void main(String[] args) {
		SpringApplication.run(MessageSystemApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		messageTypeRepository.deleteAll();
		messageRepository.deleteAll();

		List<MessageType> msgTypes = new ArrayList<>();

		msgTypes.add(new MessageType("Road Closure",1,60));
		msgTypes.add(new MessageType("Accident Alert",2,60));
		msgTypes.add(new MessageType("Traffic Jam Warning",3,60));
		msgTypes.add(new MessageType("MORNING_MESSAGE",4,60));
		msgTypes.add(new MessageType("MORNING_MESSAGE",5,60));
		msgTypes.add(new MessageType("MID_DAY_MESSAGE",6,60));
		msgTypes=messageTypeRepository.saveAll(msgTypes);

		List<Message> scheduledMsg = new ArrayList<>();

		var date = LocalDateTime.now();

		scheduledMsg.add(new Message("Road is closed a ",date.plusMinutes(2),msgTypes.get(0)));
		scheduledMsg.add(new Message("Road is closed b",date.plusMinutes(3),msgTypes.get(0)));
		scheduledMsg.add(new Message("Car Crash a ",date.plusMinutes(4),msgTypes.get(0)));
		scheduledMsg.add(new Message("Car Crash b",date.plusMinutes(5),msgTypes.get(0)));
		scheduledMsg.add(new Message("Good morning wish you a nice trip",date,msgTypes.get(3)));
		scheduledMsg.add(new Message("Good afternoon with you a nice trip",date.plusMinutes(1),msgTypes.get(4)));
		scheduledMsg.add(new Message("Good evening wish you a nice trip",date.plusMinutes(2),msgTypes.get(5)));



		messageRepository.saveAll(scheduledMsg);






	}
}
