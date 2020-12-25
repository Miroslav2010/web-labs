package mk.finki.ukim.wp.lab;

import mk.finki.ukim.wp.lab.models.Balloon;
import mk.finki.ukim.wp.lab.models.Manufacturer;
import mk.finki.ukim.wp.lab.models.Type;
import mk.finki.ukim.wp.lab.models.exceptions.BalloonNotFoundException;
import mk.finki.ukim.wp.lab.models.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.wp.lab.repository.jpa.BalloonRepository;
import mk.finki.ukim.wp.lab.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.wp.lab.service.impl.BalloonServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class EditBalloonTest {
    @Mock
    private BalloonRepository balloonRepository;
    @Mock
    private ManufacturerRepository manufacturerRepository;

    private BalloonServiceImpl service;

    @BeforeEach
    public void init()
    {
        MockitoAnnotations.initMocks(this);
        Manufacturer manufacturer = new Manufacturer("m","m","m");
        Balloon balloon = new Balloon("b","b",manufacturer, Type.A);
        Balloon editedBalloon = new Balloon("newB","newB",manufacturer, Type.C);
        Mockito.when(this.balloonRepository.findById(0L)).thenReturn(java.util.Optional.of(balloon));
        Mockito.when(this.manufacturerRepository.findById(0L)).thenReturn(java.util.Optional.of(manufacturer));
        Mockito.when(this.balloonRepository.save(Mockito.any(Balloon.class))).thenAnswer(x->x.getArgument(0));
        service = Mockito.spy(new BalloonServiceImpl(balloonRepository,manufacturerRepository));
    }

    @Test
    public void testSuccessEdit()
    {
        Manufacturer manufacturer = manufacturerRepository.findById(0L).get();
        Balloon balloon = service.edit(0L,"newB","newB",0L, Type.C);

        Mockito.verify(this.service).edit(0L,"newB","newB",0L, Type.C);
        Assert.assertEquals("Name not equal","newB",balloon.getName());
        Assert.assertEquals("Name not equal","newB",balloon.getDescription());
        Assert.assertEquals("Name not equal",manufacturer.getId(),balloon.getManufacturer().getId());
    }

    @Test
    public void TestInvalidManufacturerId()
    {
        Balloon balloonToEdit = balloonRepository.findById(0L).get();
        Assert.assertThrows("Invalid Manufacturer Id", ManufacturerNotFoundException.class,()->{

            service.edit(balloonToEdit.getId(),"newB","newB",1L, Type.C);
        }) ;
        Mockito.verify(service).edit(balloonToEdit.getId(),"newB","newB",1L, Type.C);
    }

    @Test
    public void TestInvalidBalloonId()
    {
        Assert.assertThrows("Invalid BalloonId", BalloonNotFoundException.class,()->{

            service.edit(1L,"newB","newB",0L, Type.C);
        }) ;
        Mockito.verify(service).edit(1L,"newB","newB",0L, Type.C);
    }

}
