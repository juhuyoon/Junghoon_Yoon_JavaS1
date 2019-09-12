package com.company.reccolldao.service;

import com.company.reccolldao.dao.*;
import com.company.reccolldao.model.Album;
import com.company.reccolldao.model.Artist;
import com.company.reccolldao.model.Label;
import com.company.reccolldao.model.Track;
import com.company.reccolldao.viewmodel.AlbumViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {

    private ServiceLayer service;
    private AlbumDao albumDao;
    private ArtistDao artistDao;
    private LabelDao labelDao;
    private TrackDao trackDao;


    private void setUpAlbumDaoMock() {
        albumDao = mock(AlbumDaoJdbcTemplateImpl.class);
            Album album = new Album();
            album.setId(1);
            album.setArtistId(45);
            album.setLabelId(10);
            album.setTitle("Greatest Hits");
            album.setListPrice(new BigDecimal("14.99"));
            album.setReleaseDate(LocalDate.of(1999, 05, 15));

           Album album2 = new Album();

           album2.setArtistId(45);
           album2.setLabelId(10);
           album2.setTitle("Greatest Hits");
           album2.setListPrice(new BigDecimal("14.99"));
           album2.setReleaseDate(LocalDate.of(1999, 05, 15));

           List<Album> aList = new ArrayList<>();
           aList.add(album);

           //Return the album when the DAO has added on album2
           doReturn(album).when(albumDao).addAlbum(album2);
           //Returns the album when the DAO fetches the album by Id.
           doReturn(album).when(albumDao).getAlbum(1);
           //Returns the List of albums when the DAO grabs all the albums.
           doReturn(aList).when(albumDao).getAllAlbums();
    }

    private void setUpLabelMock() {
        labelDao = mock(LabelDaoJdbcTemplateImpl.class);
            Label label = new Label();
            label.setId(10);
            label.setName("Brands");
            label.setWebsite("brand.com");

            Label label2 = new Label();
            label2.setName("Brands");
            label2.setWebsite("brand.com");

            List<Label> lList = new ArrayList<>();
            lList.add(label);

            doReturn(label).when(labelDao).addLabel(label2);
            doReturn(lList).when(labelDao).getAllLabels();
            doReturn(label).when(labelDao).getLabel(10);
    }

    private void setUpArtistMock() {
        artistDao = mock(ArtistDaoJdbcTemplateImpl.class);
            Artist artist = new Artist();
            artist.setId(45);
            artist.setInstagram("@eminem");
            artist.setName("Eminem");
            artist.setTwitter("eminem.com");

            Artist artist2 = new Artist();
            artist2.setInstagram("@eminem");
            artist2.setName("Eminem");
            artist2.setTwitter("eminem.com");

            List<Artist> artistList = new ArrayList<>();
            artistList.add(artist);

            doReturn(artist).when(artistDao).addArtist(artist2);
            doReturn(artistList).when(artistDao).getAllArtists();
            doReturn(artist).when(artistDao).getArtist(45);
    }

    private void setUpTrackMock() {
        trackDao = mock(TrackDaoJdbcTemplateImpl.class);
            Track track = new Track();
            track.setId(1);
            track.setAlbumId(1);
            track.setRunTime(100);
            track.setTitle("Number 1 Hit!");

            Track track2 = new Track();
            track2.setAlbumId(1);
            track2.setRunTime(100);
            track2.setTitle("Number 1 Hit!");

            List<Track> tList = new ArrayList<>();
            tList.add(track);

            doReturn(track).when(trackDao).addTrack(track2);
            doReturn(track).when(trackDao).getTrack(1);
            doReturn(tList).when(trackDao).getAllTracks();
            doReturn(tList).when(trackDao).getTracksByAlbum(1);
    }

    @Before
    public void setUp() throws Exception {
        setUpAlbumDaoMock();;
        setUpArtistMock();
        setUpLabelMock();
        setUpTrackMock();

        service = new ServiceLayer(albumDao, artistDao, labelDao, trackDao);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void saveFindAlbum() {
        AlbumViewModel avm = new AlbumViewModel();
        avm.setListPrice(new BigDecimal("14.99"));
        avm.setReleaseDate(LocalDate.of(1999, 05, 15));
        avm.setTitle("Greatest Hits");

        Artist artist = new Artist();
        artist.setName("Eminem");
        artist.setTwitter("eminem.com");
        artist.setInstagram("@eminem");
        artist = service.saveArtist(artist);

        avm.setArtist(artist);

        Label label = new Label();
        label.setName("Brands");
        label.setWebsite("brand.com");
        label = service.saveLabel(label);

        avm.setLabel(label);

        Track track = new Track();
        track.setAlbumId(1);
        track.setRunTime(100);
        track.setTitle("Number 1 Hit!");
        List<Track> tList = new ArrayList<>();
        tList.add(track);

        avm.setTracks(tList);

        avm = service.saveAlbum(avm);

        AlbumViewModel fromService = service.findAlbum(avm.getId());

        assertEquals(avm, fromService);

    }

    @Test
    public void findAllAlbums() {
        AlbumViewModel avm = new AlbumViewModel();
        avm.setListPrice(new BigDecimal("14.99"));
        avm.setReleaseDate(LocalDate.of(1999, 05, 15));
        avm.setTitle("Greatest Hits");

        Artist artist = new Artist();
        artist.setName("Eminem");
        artist.setTwitter("eminem.com");
        artist.setInstagram("@eminem");
        artist = service.saveArtist(artist);

        avm.setArtist(artist);

        Label label = new Label();
        label.setName("Brands");
        label.setWebsite("brand.com");
        label = service.saveLabel(label);

        avm.setLabel(label);

        Track track = new Track();
        track.setAlbumId(1);
        track.setRunTime(100);
        track.setTitle("Number 1 Hit!");
        List<Track> tList = new ArrayList<>();
        tList.add(track);

        avm.setTracks(tList);
        avm = service.saveAlbum(avm);

        List<AlbumViewModel> aList = service.findAllAlbums();
        assertEquals(1, aList.size());
    }

    @Test
    public void updateAlbum() {
        AlbumViewModel avm = new AlbumViewModel();
        avm.setId(1);
        avm.setListPrice(new BigDecimal("15.99"));
        avm.setReleaseDate(LocalDate.of(1999, 04, 15));
        avm.setTitle("Greatest Hits 2");

        Artist artist = new Artist();
        artist.setId(5);

        avm.setArtist(artist);

        Label label = new Label();
        label.setId(8);

        avm.setLabel(label);

        ArgumentCaptor<Album> albumArgumentCaptor = ArgumentCaptor.forClass(Album.class);
        doNothing().when(albumDao).updateAlbum(albumArgumentCaptor.capture());

        service.updateAlbum(avm);

        verify(albumDao,times(1)).updateAlbum(albumArgumentCaptor.getValue());
        assertEquals(1, albumArgumentCaptor.getValue().getId());
        assertEquals(avm.getListPrice(), albumArgumentCaptor.getValue().getListPrice());
        assertEquals(avm.getReleaseDate(), albumArgumentCaptor.getValue().getReleaseDate());
        assertEquals(avm.getTitle(), albumArgumentCaptor.getValue().getTitle());
    }

    @Test
    public void removeAlbum() {

        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(albumDao).deleteAlbum(integerArgumentCaptor.capture());
        service.removeAlbum(1);
        verify(albumDao, times(1)).deleteAlbum(integerArgumentCaptor.getValue());

        assertEquals(1, integerArgumentCaptor.getValue().intValue());

    }

    @Test
    public void findArtist(){
        Artist artist = new Artist();
        artist.setInstagram("@eminem");
        artist.setName("Eminem");
        artist.setTwitter("eminem.com");

        artist = service.saveArtist(artist);
        Artist fromService = service.findArtist(artist.getId());

        assertEquals(artist, fromService);

    }

    @Test
    public void findAllArtists() {
        Artist artist = new Artist();
        artist.setInstagram("@eminem");
        artist.setName("Eminem");
        artist.setTwitter("eminem.com");

        artist = service.saveArtist(artist);

        List<Artist> artistList = service.findAllArtists();
        assertEquals(1, artistList.size());
    }

    @Test
    public void saveFindFindAllArtist() {
        Artist artist = new Artist();
        artist.setInstagram("@eminem");
        artist.setName("Eminem");
        artist.setTwitter("eminem.com");

        artist = service.saveArtist(artist);
        Artist fromService = service.findArtist(artist.getId());
        assertEquals(artist, fromService);

        List<Artist> aList = service.findAllArtists();
        assertEquals(1, aList.size());
        assertEquals(artist, aList.get(0));
    }

    @Test
    public void saveFindFindAllLabel() {
        Label label = new Label();
        label.setName("Brands");
        label.setWebsite("brand.com");

        label = service.saveLabel(label);
        Label fromService = service.findLabel(label.getId());
        assertEquals(label, fromService);

        List<Label> lList = service.findAllLabels();
        assertEquals(1, lList.size());
        assertEquals(label, lList.get(0));

    }
    @Test
    public void saveLabel() {
        Label label = new Label();
        label.setName("Brands");
        label.setWebsite("brand.com");

        label = service.saveLabel(label);

        Label fromService = service.findLabel(label.getId());

        assertEquals(label, fromService);
    }

    @Test
    public void findLabel() {
        Label label = new Label();
        label.setName("Brands");
        label.setWebsite("brand.com");

        label = service.saveLabel(label);

        Label fromService = service.findLabel(label.getId());

        assertEquals(label, fromService);
    }

    @Test
    public void updateLabel() {

        Label label = new Label();
        label.setId(8);

        ArgumentCaptor<Label> labelArgumentCaptor = ArgumentCaptor.forClass(Label.class);
        doNothing().when(labelDao).updateLabel(labelArgumentCaptor.capture());

        service.updateLabel(label);

        verify(labelDao,times(1)).updateLabel(labelArgumentCaptor.getValue());
        assertEquals(8, labelArgumentCaptor.getValue().getId());

    }

    @Test
    public void removeLabel() {
//       Label label = new Label();
//       label.setId(10);

       ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
       doNothing().when(labelDao).deleteLabel(integerArgumentCaptor.capture());
       service.removeLabel(10);
       verify(labelDao, times(1)).deleteLabel(integerArgumentCaptor.getValue());

       assertEquals(10, integerArgumentCaptor.getValue().intValue());
    }

}