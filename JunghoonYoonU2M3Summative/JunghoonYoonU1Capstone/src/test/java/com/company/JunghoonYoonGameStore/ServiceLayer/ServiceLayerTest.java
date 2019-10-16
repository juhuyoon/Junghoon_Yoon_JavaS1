package com.company.JunghoonYoonGameStore.ServiceLayer;

import com.company.JunghoonYoonGameStore.DAO.*;
import com.company.JunghoonYoonGameStore.DTO.*;
import com.company.JunghoonYoonGameStore.ViewModel.OrderViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceLayerTest {

    //Mock is for unit tests From Mockito library, same as doing the gameDao = mock(gameDao.class)
    //MockBeans is for integration tests (it actually creates a bean)
        //Part of Spring, and mockBean creates a mock instance in the ApplicationContext and then it will be injected
        //into the instance.

    @InjectMocks
    private ServiceLayer service;
    @Mock
    private GameDao gameDao;
    @Mock
    private ConsoleDao consoleDao;
    @Mock
    private TshirtDao tshirtDao;
    @Mock
    private InvoiceDao invoiceDao;
    @Mock
    private ProcessingFeesDao pFeesDao;
    @Mock
    private SalesTaxRate_Dao salesTaxDao;

    @Before
    public void setUp() throws Exception {
        setUpGameDaoMock();
        setUpConsoleMock();
        setUpTShirtMock();
        setUpPFeesMock();
        setUpSalesTaxMock();
        setUpInvoiceMock();

        //This is filled with the InjectMocks Annotation
//        service = new ServiceLayer(gameDao, consoleDao, tshirtDao, pFeesDao, salesTaxDao, invoiceDao);
    }

    @Test
    public void addGetOrder() {
        OrderViewModel ovm = new OrderViewModel();
        ovm.setName("Jung");
        ovm.setStreet("123 Kappa ST");
        ovm.setCity("Atlanta");
        ovm.setState("GA");
        ovm.setZipcode("30033");
        ovm.setItem_type("Games");
        ovm.setItem_id(1);
        ovm.setQuantity(10);

        Invoice invoice = new Invoice();
        invoice.setInvoice_id(1);
        invoice.setName(ovm.getName());
        invoice.setStreet(ovm.getStreet());
        invoice.setCity(ovm.getCity());
        invoice.setState(ovm.getState());
        invoice.setZipcode(ovm.getZipcode());
        invoice.setItem_type(ovm.getItem_type());
        invoice.setItem_id(ovm.getItem_id());
        invoice.setQuantity(ovm.getQuantity());
        invoice.setUnit_price(new BigDecimal("15.99"));
        invoice.setSubtotal(new BigDecimal("159.99"));
        invoice.setTax(new BigDecimal("0.07"));
        invoice.setProcessing_fee(new BigDecimal("15.99"));
        invoice.setTotal(new BigDecimal("500.00"));

        invoiceDao.addInvoice(invoice);
        Invoice fromService = service.getOrder(invoice.getInvoice_id());


        assertEquals(invoice, fromService);
    }

    @Test
    public void addGetGetAllConsole() {
        Console console = new Console();
        console.setModel("XBox");
        console.setManufacturer("Windows");
        console.setMemory_amount("500");
        console.setProcessor("IBM");
        console.setPrice(new BigDecimal("159.99"));
        console.setQuantity(10);

        console = service.addConsole(console);
        Console fromService = service.getConsole(console.getConsole_id());
        assertEquals(console, fromService);

        List<Console> cList = service.getAllConsoles();
        assertEquals(1, cList.size());
        assertEquals(console, cList.get(0));
    }

    @Test
    public void updateConsole() {
        Console console = new Console();
        console.setConsole_id(2);

        ArgumentCaptor<Console> consoleArgumentCaptor = ArgumentCaptor.forClass(Console.class);
        doNothing().when(consoleDao).updateConsole(consoleArgumentCaptor.capture());

        service.updateConsole(console);

        verify(consoleDao, times(1)).updateConsole(consoleArgumentCaptor.getValue());
        assertEquals((Integer) 2, consoleArgumentCaptor.getValue().getConsole_id());
    }

    @Test
    public void deleteConsole() {
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(consoleDao).deleteConsole(integerArgumentCaptor.capture());
        service.deleteConsole(1);
        verify(consoleDao, times(1)).deleteConsole(integerArgumentCaptor.getValue());

        assertEquals(1, integerArgumentCaptor.getValue().intValue());

    }

    @Test
    public void addGetGetAllGame() {
        Game game = new Game();
        game.setTitle("Starcraft");
        game.setEsrb_rating("T");
        game.setDescription("RTS GAME");
        game.setPrice(new BigDecimal("24.99"));
        game.setStudio("Blizzard");
        game.setQuantity(10);

        game = service.addGame(game);
        Game fromService = service.getGame(game.getGame_id());
        assertEquals(game, fromService);

        List<Game> gList = service.getAllGames();
        assertEquals(1, gList.size());
        assertEquals(game, gList.get(0));
    }

    @Test
    public void updateGame() {
        Game game = new Game();
        game.setGame_id(14);

        ArgumentCaptor<Game> gameArgumentCaptor = ArgumentCaptor.forClass(Game.class);
        doNothing().when(gameDao).updateGame(gameArgumentCaptor.capture());

        service.updateGame(game);

        verify(gameDao, times(1)).updateGame(gameArgumentCaptor.getValue());

        assertEquals((Integer) 14, gameArgumentCaptor.getValue().getGame_id());
    }

    @Test
    public void deleteGame() {
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(gameDao).deleteGame(integerArgumentCaptor.capture());
        service.deleteGame(1);
        verify(gameDao, times(1)).deleteGame(integerArgumentCaptor.getValue());

        assertEquals(1, integerArgumentCaptor.getValue().intValue());
    }

    @Test
    public void addGetGetAllTShirt() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("M");
        tShirt.setColor("Orange");
        tShirt.setDescription("Orange and casual");
        tShirt.setPrice(new BigDecimal("15.99"));
        tShirt.setQuantity(5);

        tShirt = service.addTShirt(tShirt);
        TShirt fromService = service.getTShirt(tShirt.getT_shirt_id());
        assertEquals(tShirt, fromService);

        List<TShirt> tList = service.getAllTShirts();
        assertEquals(1, tList.size());
        assertEquals(tShirt, tList.get(0));
    }

    @Test
    public void updateTShirt() {
        TShirt tShirt = new TShirt();
        tShirt.setT_shirt_id(5);

        ArgumentCaptor<TShirt> tShirtArgumentCaptor = ArgumentCaptor.forClass(TShirt.class);
        doNothing().when(tshirtDao).updateTShirt(tShirtArgumentCaptor.capture());

        service.updateTShirt(tShirt);

        verify(tshirtDao, times(1)).updateTShirt(tShirtArgumentCaptor.getValue());
        assertEquals((Integer)5, tShirtArgumentCaptor.getValue().getT_shirt_id());
    }

    @Test
    public void deleteShirt() {
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(tshirtDao).deleteTShirt(integerArgumentCaptor.capture());
        service.deleteShirt(5);
        verify(tshirtDao, times(1)).deleteTShirt(integerArgumentCaptor.getValue());

        assertEquals(5, integerArgumentCaptor.getValue().intValue());
    }

    @Test
    public void addGetGetAllPFees() {
        Processing_Fee pFee = new Processing_Fee();
        pFee.setProduct_type("Games");
        pFee.setFee(new BigDecimal("1.49"));

        pFee = service.addPFees(pFee);
        Processing_Fee fromService = service.getPFees(pFee.getProduct_type()); //coming back null here as well probably.
        assertEquals(pFee, fromService);

        List<Processing_Fee> pList = service.getAllpFees();
        assertEquals(1, pList.size());
        assertEquals(pFee, pList.get(0));
    }


    @Test
    public void updatePFees() {
        Processing_Fee pFee = new Processing_Fee();
        pFee.setProduct_type("TYPE");

        ArgumentCaptor<Processing_Fee> processing_feeArgumentCaptor = ArgumentCaptor.forClass(Processing_Fee.class);
        doNothing().when(pFeesDao).updateProcessingFees(processing_feeArgumentCaptor.capture());

        service.updatePFees(pFee);

        verify(pFeesDao, times(1)).updateProcessingFees(processing_feeArgumentCaptor.getValue());
        assertEquals("TYPE", processing_feeArgumentCaptor.getValue().getProduct_type());
    }

    @Test
    public void deletePFees() {
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        doNothing().when(pFeesDao).deleteProcessingFees(stringArgumentCaptor.capture());

        service.deletePFees("TYPE2");
        verify(pFeesDao, times(1)).deleteProcessingFees(stringArgumentCaptor.getValue());
        assertEquals("TYPE2", stringArgumentCaptor.getValue().toString());
    }

    @Test
    public void addGetGetAllSalesTax() {
        Sales_Tax_Rate salesTax = new Sales_Tax_Rate();
        salesTax.setState("CA");
        salesTax.setRate(new BigDecimal("0.07"));

        salesTax = service.addSalesTax(salesTax);
        Sales_Tax_Rate fromService = service.getSalesTax("CA"); //coming back null here.
        assertEquals(salesTax, fromService);

        List<Sales_Tax_Rate> sList = service.getAllSalesTax();
        assertEquals(1, sList.size());
        assertEquals(salesTax, sList.get(0));
    }

    @Test
    public void updateSalesTax() {
        Sales_Tax_Rate salesTax = new Sales_Tax_Rate();
        salesTax.setState("CA");

        ArgumentCaptor<Sales_Tax_Rate> sales_tax_rateArgumentCaptor = ArgumentCaptor.forClass(Sales_Tax_Rate.class);
        doNothing().when(salesTaxDao).updateSalesTaxRate(sales_tax_rateArgumentCaptor.capture());

        service.updateSalesTax(salesTax);

        verify(salesTaxDao, times(1)).updateSalesTaxRate(sales_tax_rateArgumentCaptor.getValue());
        assertEquals("CA", sales_tax_rateArgumentCaptor.getValue().getState());
    }

    @Test
    public void deleteSalesTax() {
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        doNothing().when(salesTaxDao).deleteSalesTaxRate(stringArgumentCaptor.capture());
        service.deleteSalesTax("CA");
        verify(salesTaxDao, times(1)).deleteSalesTaxRate(stringArgumentCaptor.getValue());

        assertEquals("CA", stringArgumentCaptor.getValue().toString());
    }

    //Helper Methods
    private void setUpGameDaoMock() {
       // gameDao = mock(GameDaoTempImpl.class);
        Game game = new Game();
        game.setGame_id(1);
        game.setTitle("Starcraft");
        game.setEsrb_rating("T");
        game.setDescription("RTS GAME");
        game.setPrice(new BigDecimal("24.99"));
        game.setStudio("Blizzard");
        game.setQuantity(10);

        Game game2 = new Game();
        game2.setTitle("Starcraft");
        game2.setEsrb_rating("T");
        game2.setDescription("RTS GAME");
        game2.setPrice(new BigDecimal("24.99"));
        game2.setStudio("Blizzard");
        game2.setQuantity(10);

        List<Game> gList = new ArrayList<>();
        gList.add(game);

        doReturn(game).when(gameDao).addGame(game2);
        doReturn(game).when(gameDao).getGame(1);
        doReturn(gList).when(gameDao).getAllGames();
//        doReturn(gList).when(gameDao).getGamesByTitle("Starcraft");
//        doReturn(gList).when(gameDao).getGamesByRating("T");
//        doReturn(gList).when(gameDao).getGamesByStudio("Blizzard");
    }

    private void setUpConsoleMock() {
        //consoleDao = mock(ConsoleDaoTempImpl.class);
        Console console = new Console();
        console.setConsole_id(1);
        console.setModel("XBox");
        console.setManufacturer("Windows");
        console.setMemory_amount("500");
        console.setProcessor("IBM");
        console.setPrice(new BigDecimal("159.99"));
        console.setQuantity(10);

        Console console2 = new Console();
        console2.setModel("XBox");
        console2.setManufacturer("Windows");
        console2.setMemory_amount("500");
        console2.setProcessor("IBM");
        console2.setPrice(new BigDecimal("159.99"));
        console2.setQuantity(10);

        List<Console> cList = new ArrayList<>();
        cList.add(console);

        doReturn(console).when(consoleDao).addConsole(console2);
        doReturn(console).when(consoleDao).getConsole(1);
        doReturn(cList).when(consoleDao).getAllConsoles();
       // doReturn(cList).when(consoleDao).getConsolesByManufacturer("Windows");
    }

    private void setUpTShirtMock() {
        //tshirtDao = mock(TShirtDaoTempImpl.class);
        TShirt tShirt = new TShirt();
        tShirt.setT_shirt_id(1);
        tShirt.setSize("M");
        tShirt.setColor("Orange");
        tShirt.setDescription("Orange and casual");
        tShirt.setPrice(new BigDecimal("15.99"));
        tShirt.setQuantity(5);

        TShirt tShirt2 = new TShirt();
        tShirt2.setSize("M");
        tShirt2.setColor("Orange");
        tShirt2.setDescription("Orange and casual");
        tShirt2.setPrice(new BigDecimal("15.99"));
        tShirt2.setQuantity(5);

        List<TShirt> tList = new ArrayList<>();
        tList.add(tShirt);

        doReturn(tShirt).when(tshirtDao).addTShirt(tShirt2);
        doReturn(tShirt).when(tshirtDao).getTShirt(1);
        doReturn(tList).when(tshirtDao).getAllTShirts();
//        doReturn(tList).when(tshirtDao).getTShirtsBySize("M");
//        doReturn(tList).when(tshirtDao).getTShirtsByColor("Orange");
    }

    private void setUpSalesTaxMock() {
        //salesTaxDao = mock(SalesTaxRateDaoTempImpl.class);
        Sales_Tax_Rate salesTax = new Sales_Tax_Rate();
        salesTax.setState("CA");
        salesTax.setRate(new BigDecimal("0.07"));

        Sales_Tax_Rate salesTax2 = new Sales_Tax_Rate();
        salesTax2.setState("GA");
        salesTax2.setRate(new BigDecimal("0.07"));

        List<Sales_Tax_Rate> sList = new ArrayList<>();
        sList.add(salesTax);

        doReturn(salesTax).when(salesTaxDao).addSalesTaxRate(salesTax);
        doReturn(salesTax).when(salesTaxDao).getSalesTaxRate("CA");
        doReturn(sList).when(salesTaxDao).getAllSalesTaxRate();
    }

    private void setUpPFeesMock() {
       //pFeesDao = mock(ProcessingFeesDaoTempImpl.class);
        Processing_Fee pFee = new Processing_Fee();
        pFee.setProduct_type("Games");
        pFee.setFee(new BigDecimal("1.49"));

        Processing_Fee pFee2 = new Processing_Fee();
        pFee2.setProduct_type("Games");
        pFee2.setFee(new BigDecimal("1.49"));

        List<Processing_Fee> pList = new ArrayList<>();
        pList.add(pFee);

        doReturn(pFee).when(pFeesDao).addProcessingFees(pFee2);
        doReturn(pFee).when(pFeesDao).getProcessingFees("Games");
        doReturn(pList).when(pFeesDao).getAllProcessingFees();
    }

    private void setUpInvoiceMock() {
        //invoiceDao = mock(InvoiceDaoTempImpl.class);
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(1);
        invoice.setName("Jung");
        invoice.setStreet("123 Kappa ST");
        invoice.setCity("Atlanta");
        invoice.setState("GA");
        invoice.setZipcode("30033");
        invoice.setItem_type("Games");
        invoice.setItem_id(1);
        invoice.setUnit_price(new BigDecimal("15.99"));
        invoice.setQuantity(10);
        invoice.setSubtotal(new BigDecimal("159.99"));
        invoice.setTax(new BigDecimal("0.07"));
        invoice.setProcessing_fee(new BigDecimal("15.99"));
        invoice.setTotal(new BigDecimal("500.00"));

        Invoice invoice2 = new Invoice();
        invoice2.setName("Jung");
        invoice2.setState("123 Kappa ST");
        invoice2.setCity("Atlanta");
        invoice2.setState("GA");
        invoice2.setZipcode("30033");
        invoice2.setItem_type("Games");
        invoice2.setItem_id(1);
        invoice2.setUnit_price(new BigDecimal("15.99"));
        invoice2.setQuantity(10);
        invoice2.setSubtotal(new BigDecimal("159.99"));
        invoice2.setTax(new BigDecimal("0.07"));
        invoice2.setProcessing_fee(new BigDecimal("15.99"));
        invoice2.setTotal(new BigDecimal("500.00"));

        List<Invoice> iList = new ArrayList<>();
        iList.add(invoice);

        //doReturn(invoice).when(invoiceDao).addInvoice(invoice2);
        doReturn(invoice).when(invoiceDao).getInvoice(1);
        //doReturn(iList).when(invoiceDao).getAllInvoice();
    }

    private void setUpOVMMock() {
        //invoiceDao = mock(InvoiceDaoTempImpl.class);
        OrderViewModel ovm = new OrderViewModel();
        ovm.setOrder_id(1);
        ovm.setName("Jung");
        ovm.setStreet("123 Kappa ST");
        ovm.setCity("Atlanta");
        ovm.setState("GA");
        ovm.setZipcode("30033");
        ovm.setItem_type("Games");
        ovm.setItem_id(1);
        ovm.setQuantity(10);

    }


    @After
    public void tearDown() throws Exception {

    }
}