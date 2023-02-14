package com.involveininnovation.chat;

import com.involveininnovation.chat.entities.*;
import com.involveininnovation.chat.repositories.AdminRepository;
import com.involveininnovation.chat.repositories.ClientRepository;
import com.involveininnovation.chat.repositories.DeviceRepository;
import com.involveininnovation.chat.repositories.SensorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@SpringBootApplication
@Validated
public class ChatApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ChatApplication.class);
	}

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(ChatApplication.class, args);
	}


	@Bean
	CommandLineRunner init(AdminRepository adminRepository, ClientRepository clientRepository, DeviceRepository deviceRepository, SensorRepository sensorRepository){
		return args -> {
			UserAuth admin1 = new Admin(10000L, "admin", "admin", "Lucian", "Cluj", "01/01/2000");
			UserAuth client1 = new Client(12691L, "user1", "user1", "user userescu", "Cluj", "27/01/2000");
			Device house1 = new Device( (Client)client1, "Smart house1", 100, 50);
			SensorInfo sensorInfo1 = new SensorInfo("7:00", 100);
			SensorInfo sensorInfo2 = new SensorInfo("9:00", 110);
			List<SensorInfo> sensorInfos = new ArrayList<>();
			sensorInfos.add(sensorInfo1);
			sensorInfos.add(sensorInfo2);
			Sensor sensor1 = new Sensor( house1, "Description1", 100, sensorInfos);

			clientRepository.save((Client)client1);
			adminRepository.save((Admin)admin1);
			deviceRepository.save(house1);
			sensorRepository.save(sensor1);

		};
	}

}
