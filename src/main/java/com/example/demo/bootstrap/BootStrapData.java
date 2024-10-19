package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Clear the repositories (for testing only)
        partRepository.deleteAll();
        outsourcedPartRepository.deleteAll();
        productRepository.deleteAll();
        

         // Check if Inhouse Parts repository is empty
        if (partRepository.count() == 0) {
            InhousePart seatCushion = new InhousePart();
            seatCushion.setName("Seat Cushion");
            seatCushion.setPrice(40.00);
            seatCushion.setInv(50);

            InhousePart backrest = new InhousePart();
            backrest.setName("Backrest");
            backrest.setPrice(60.00);
            backrest.setInv(30);

            InhousePart wheels = new InhousePart();
            wheels.setName("Wheels");
            wheels.setPrice(15.00);
            wheels.setInv(100);

            InhousePart armrests = new InhousePart();
            armrests.setName("Armrests");
            armrests.setPrice(20.00);
            armrests.setInv(60);

            InhousePart adjustmentLever = new InhousePart();
            adjustmentLever.setName("Seat Adjustment Lever");
            adjustmentLever.setPrice(10.00);
            adjustmentLever.setInv(70);

            partRepository.save(seatCushion);
            partRepository.save(backrest);
            partRepository.save(wheels);
            partRepository.save(armrests);
            partRepository.save(adjustmentLever);
        }
        
         // Check and add sample outsourced parts
         if (outsourcedPartRepository.count() == 0) {
            OutsourcedPart seatCushionCover = new OutsourcedPart();
            seatCushionCover.setName("Seat Cushion Cover");
            seatCushionCover.setPrice(24.99);
            seatCushionCover.setInv(20);
            seatCushionCover.setCompanyName("FabricPro");
        
            OutsourcedPart hydraulicLift = new OutsourcedPart();
            hydraulicLift.setName("Hydraulic Lift");
            hydraulicLift.setPrice(49.99);
            hydraulicLift.setInv(15);
            hydraulicLift.setCompanyName("MechaCo");
        
            OutsourcedPart wheelBaseAssembly = new OutsourcedPart();
            wheelBaseAssembly.setName("Wheel Base Assembly");
            wheelBaseAssembly.setPrice(34.99);
            wheelBaseAssembly.setInv(30);
            wheelBaseAssembly.setCompanyName("WheelFactory");
        
            OutsourcedPart lumbarSupportPadding = new OutsourcedPart();
            lumbarSupportPadding.setName("Lumbar Support Padding");
            lumbarSupportPadding.setPrice(19.99);
            lumbarSupportPadding.setInv(25);
            lumbarSupportPadding.setCompanyName("ErgoTech");
        
            OutsourcedPart armrestPads = new OutsourcedPart();
            armrestPads.setName("Armrest Pads");
            armrestPads.setPrice(14.99);
            armrestPads.setInv(40);
            armrestPads.setCompanyName("CushionWorld");
        
            outsourcedPartRepository.save(seatCushionCover);
            outsourcedPartRepository.save(hydraulicLift);
            outsourcedPartRepository.save(wheelBaseAssembly);
            outsourcedPartRepository.save(lumbarSupportPadding);
            outsourcedPartRepository.save(armrestPads);
        }

        // Check and add sample products
        if (productRepository.count() == 0) {
            Product officeChairA = new Product("Office Chair A", 199.99, 10);
            Product officeChairB = new Product("Office Chair B", 249.99, 8);
            Product officeChairC = new Product("Office Chair C", 299.99, 7);
            Product officeChairD = new Product("Office Chair D", 399.99, 13);
            Product officeChairE = new Product("Office Chair E", 599.99, 2);

            productRepository.save(officeChairA);
            productRepository.save(officeChairB);
            productRepository.save(officeChairC);
            productRepository.save(officeChairD);
            productRepository.save(officeChairE);
        }



        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
