package com.blockchain.data.anonymization;

import com.blockchain.data.anonymization.dto.Block;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class DataAnonymizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataAnonymizationApplication.class, args);

		/*List<Block> blockchain = new ArrayList<>();
		int prefix = 4;
		String prefixString = new String(new char[prefix]).replace('\0', '0');

		Block newBlock = new Block(
				"The is a New Block.",
				blockchain.get(blockchain.size() - 1).getHash(),
				new Date().getTime());
		String s = newBlock.mineBlock(prefix);
		System.out.println(s);*/
	}

}
