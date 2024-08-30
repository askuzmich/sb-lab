package com.example.demo.demoDBInitializer;

import com.example.demo.endpoints.headObject.HeadObject;
import com.example.demo.endpoints.headObject.HeadObjectRepository;
import com.example.demo.endpoints.subEntity.SubEntity;
import com.example.demo.endpoints.subEntity.SubEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoDBInitializer implements CommandLineRunner {
    private final SubEntityRepository subEntityRepository;

    private final HeadObjectRepository headObjectRepository;

    public DemoDBInitializer(SubEntityRepository subEntityRepository, HeadObjectRepository headObjectRepository) {
        this.subEntityRepository = subEntityRepository;
        this.headObjectRepository = headObjectRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        SubEntity se1 = new SubEntity();
        se1.setId("110022");
        se1.setName("se1");
        se1.setDescription("woo-hoo se1");
        se1.setImgUrl("https://fakeImageUrl.com/se1");

        SubEntity se2 = new SubEntity();
        se2.setId("110033");
        se2.setName("se2");
        se2.setDescription("woo-hoo se2");
        se2.setImgUrl("https://fakeImageUrl.com/se2");

        SubEntity se3 = new SubEntity();
        se3.setId("110044");
        se3.setName("se3");
        se3.setDescription("woo-hoo se3");
        se3.setImgUrl("https://fakeImageUrl.com/se3");

        SubEntity se4 = new SubEntity();
        se4.setId("110055");
        se4.setName("se4");
        se4.setDescription("woo-hoo se4");
        se4.setImgUrl("https://fakeImageUrl.com/se4");

        SubEntity se5 = new SubEntity();
        se5.setId("110066");
        se5.setName("se5");
        se5.setDescription("woo-hoo se5");
        se5.setImgUrl("https://fakeImageUrl.com/se5");

        HeadObject ho1 = new HeadObject();
        ho1.setId(1);
        ho1.setName("ho1");
        ho1.addSubEntity(se1);

        HeadObject ho2 = new HeadObject();
        ho2.setId(2);
        ho2.setName("ho2");
        ho2.addSubEntity(se2);
        ho2.addSubEntity(se3);

        HeadObject ho3 = new HeadObject();
        ho3.setId(3);
        ho3.setName("ho3");
        ho3.addSubEntity(se4);


        headObjectRepository.save(ho1);
        headObjectRepository.save(ho2);
        headObjectRepository.save(ho3);

        subEntityRepository.save(se5);
    }
}
