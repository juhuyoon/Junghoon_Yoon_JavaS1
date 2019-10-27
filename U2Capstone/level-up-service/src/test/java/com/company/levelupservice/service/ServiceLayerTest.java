package com.company.levelupservice.service;

import com.company.levelupservice.dao.LevelUpDao;
import com.company.levelupservice.model.LevelUp;
import com.company.levelupservice.model.LevelViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {

    private LevelUpDao levelUpDao;
    private ServiceLayer serviceLayer;

    private ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
    private ArgumentCaptor<LevelUp> objectArgumentCaptor = ArgumentCaptor.forClass(LevelUp.class);

    @Before
    public void setUp() throws Exception {
        setUpLevelUpDao();
        serviceLayer = new ServiceLayer(levelUpDao);
    }

    private void setUpLevelUpDao(){
        levelUpDao = mock(LevelUpDao.class);

        //plain objects - no id
        LevelUp lvl1New = new LevelUp(
                1,
                20,
                LocalDate.of(2000,01,01)
        );

        LevelUp lvl2New = new LevelUp(
                2,
                42,
                LocalDate.of(2000,02,02)
        );

        //plain objects - saved
        LevelUp lvl1Saved = new LevelUp(
                1,
                1,
                20,
                LocalDate.of(2000,01,01)
        );

        LevelUp lvl2Saved = new LevelUp(
                2,
                2,
                42,
                LocalDate.of(2000,02,02)
        );

        List<LevelUp> expectedLvl = new ArrayList<>();
        expectedLvl.add(lvl1Saved);
        expectedLvl.add(lvl2Saved);

        //view model - no id
        LevelViewModel lvm1New = new LevelViewModel(
                1,
                20,
                LocalDate.of(2000,01,01)
        );

        LevelViewModel lvm2New = new LevelViewModel(
                2,
                42,
                LocalDate.of(2000,02,02)
        );
        //view model - saved
        LevelViewModel lvm1Saved = new LevelViewModel(
                1,
                1,
                20,
                LocalDate.of(2000,01,01)
        );

        LevelViewModel lvm2Saved = new LevelViewModel(
                2,
                2,
                42,
                LocalDate.of(2000,02,02)
        );
        List<LevelViewModel> expectedLvm = new ArrayList<>();
        expectedLvm.add(lvm1Saved);
        expectedLvm.add(lvm2Saved);

        doReturn(lvl1Saved).when(levelUpDao).createLevelUp(lvl1New);
        doReturn(lvl2Saved).when(levelUpDao).createLevelUp(lvl2New);
        doReturn(lvl1Saved).when(levelUpDao).readLevelUp(1);
        doReturn(lvl2Saved).when(levelUpDao).readLevelUp(2);
        doReturn(expectedLvl).when(levelUpDao).readAll();
        doReturn(lvl1Saved).when(levelUpDao).readByCustomer(1);
        doReturn(lvl2Saved).when(levelUpDao).readByCustomer(2);
        doNothing().when(levelUpDao).updateLevelUp(objectArgumentCaptor.capture());
        doNothing().when(levelUpDao).deleteLevelUp(integerArgumentCaptor.capture());
    }

    @Test
    public void addLevelUpEntry() {
        LevelViewModel lvm1New = new LevelViewModel(
                1,
                20,
                LocalDate.of(2000,01,01)
        );

        LevelViewModel checkLvm = serviceLayer.addLevelUpEntry(lvm1New);

        assertNotNull(checkLvm.getLevel_up_id());
        assertTrue(checkLvm.getLevel_up_id() > 0);
        assertEquals(lvm1New.getCustomer_id(), checkLvm.getCustomer_id());
    }

    @Test
    public void findLevelUpEntry() {
        LevelViewModel lvm1Saved = new LevelViewModel(
                1,
                1,
                20,
                LocalDate.of(2000,01,01)
        );

        LevelViewModel checkLvm = serviceLayer.findLevelUpEntry(1);

        assertEquals(lvm1Saved, checkLvm);
    }

    @Test
    public void findAllLevelUpEntries() {
        LevelViewModel lvm1Saved = new LevelViewModel(
                1,
                1,
                20,
                LocalDate.of(2000,01,01)
        );

        LevelViewModel lvm2Saved = new LevelViewModel(
                2,
                2,
                42,
                LocalDate.of(2000,02,02)
        );
        List<LevelViewModel> expectedLvm = new ArrayList<>();
        expectedLvm.add(lvm1Saved);
        expectedLvm.add(lvm2Saved);

        List<LevelViewModel> checkList = serviceLayer.findAllLevelUpEntries();

        assertEquals(expectedLvm, checkList);
    }

    @Test
    public void findLevelUpEntryByCustomerId() {
        LevelViewModel lvm1Saved = new LevelViewModel(
                1,
                1,
                20,
                LocalDate.of(2000,01,01)
        );

        LevelViewModel checkLvm = serviceLayer.findLevelUpByCustomer(1);

        assertEquals(lvm1Saved, checkLvm);
    }

    @Test
    public void updateLevelUpEntry() {
        LevelViewModel lvmUpdate = new LevelViewModel(
                1,
                1,
                100,
                LocalDate.of(2000,01,01)
        );

        LevelUp lvlUpdated = new LevelUp(
                1,
                1,
                100,
                LocalDate.of(2000,01,01)
        );

        serviceLayer.updateLevelUpEntry(lvmUpdate);

        verify(levelUpDao, times(1)).updateLevelUp(lvlUpdated);
        assertEquals(lvlUpdated, objectArgumentCaptor.getValue());
    }

    @Test
    public void removeLevelUpEntry() {
        serviceLayer.removeLevelUpEntry(1);

        verify(levelUpDao, times(1)).deleteLevelUp(1);
        int argcap = integerArgumentCaptor.getValue();
        assertEquals(1, argcap);
    }
}