package io.github.kuyer.rpc.codec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.github.kuyer.rpc.demo.api.model.CustomerModel;

/**
 * 序列号工具测试
 * @author Rory.Zhang
 */
public class CodecTest {
	
	public static void main(String[] args) throws Exception {
		List<CustomerModel> customers = new ArrayList<CustomerModel>();
		for(int i=0; i<100000; i++) {
			CustomerModel model = new CustomerModel();
			model.setId(i);
			model.setName("rory-"+i);
			model.setBirthday(new Date());
			customers.add(model);
		}
		TestModel test = new TestModel();
		test.setCustomers(customers);
		
		TestModel test1 = null;
		long start = 0l;
		long time = 0l;
		byte[] datas = new byte[0];
		
		start = System.currentTimeMillis();
		datas = BaseCodec.encode(test);
		time = System.currentTimeMillis()-start;
		System.out.println("BaseCodec.encode size: "+datas.length+"; time: "+time);
		
		start = System.currentTimeMillis();
		test1 = (TestModel) BaseCodec.decode(datas);
		time = System.currentTimeMillis()-start;
		System.out.println("BaseCodec.decode datas: "+test1.getCustomers().size()+"; time: "+time);
		
		System.out.println("------------------------------------------------------------");
		
		start = System.currentTimeMillis();
		datas = ProtostuffCodec.encode(test);
		time = System.currentTimeMillis()-start;
		System.out.println("ProtostuffCodec.encode size: "+datas.length+"; time: "+time);
		
		start = System.currentTimeMillis();
		test1 = ProtostuffCodec.decode(datas, TestModel.class);
		time = System.currentTimeMillis()-start;
		System.out.println("ProtostuffCodec.decode datas: "+test1.getCustomers().size()+"; time: "+time);
		
		System.out.println("------------------------------------------------------------");
		
		start = System.currentTimeMillis();
		datas = FstCodec.encode(test);
		time = System.currentTimeMillis()-start;
		System.out.println("FstCodec.encode size: "+datas.length+"; time: "+time);
		
		start = System.currentTimeMillis();
		test1 = (TestModel) FstCodec.decode(datas);
		time = System.currentTimeMillis()-start;
		System.out.println("FstCodec.decode datas: "+test1.getCustomers().size()+"; time: "+time);
		
		System.out.println("------------------------------------------------------------");
		
		start = System.currentTimeMillis();
		datas = KryoCodec.encode(test);
		time = System.currentTimeMillis()-start;
		System.out.println("KryoCodec.encode size: "+datas.length+"; time: "+time);
		
		start = System.currentTimeMillis();
		test1 = (TestModel) KryoCodec.decode(datas);
		time = System.currentTimeMillis()-start;
		System.out.println("KryoCodec.decode datas: "+test1.getCustomers().size()+"; time: "+time);
		
		/* 第一次运行结果
		BaseCodec.encode size: 4589267; time: 984
		BaseCodec.decode datas: 100000; time: 594
		------------------------------------------------------------
		ProtostuffCodec.encode size: 2672378; time: 516
		ProtostuffCodec.decode datas: 100000; time: 125
		------------------------------------------------------------
		FstCodec.encode size: 3323191; time: 562
		FstCodec.decode datas: 100000; time: 547
		------------------------------------------------------------
		KryoCodec.encode size: 2680759; time: 797
		KryoCodec.decode datas: 100000; time: 266
		*/
		
		/* 第二次运行结果
		BaseCodec.encode size: 4589267; time: 1063
		BaseCodec.decode datas: 100000; time: 625
		------------------------------------------------------------
		ProtostuffCodec.encode size: 2672378; time: 515
		ProtostuffCodec.decode datas: 100000; time: 125
		------------------------------------------------------------
		FstCodec.encode size: 3323191; time: 563
		FstCodec.decode datas: 100000; time: 578
		------------------------------------------------------------
		KryoCodec.encode size: 2680759; time: 781
		KryoCodec.decode datas: 100000; time: 266
		*/
		
	}

}
