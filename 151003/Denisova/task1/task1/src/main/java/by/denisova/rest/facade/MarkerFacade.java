package by.denisova.rest.facade;

import by.denisova.rest.mapper.MarkerMapper;
import by.denisova.rest.service.MarkerService;
import by.denisova.rest.model.Marker;
import by.denisova.rest.dto.request.CreateMarkerDto;
import by.denisova.rest.dto.request.UpdateMarkerDto;
import by.denisova.rest.dto.response.MarkerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MarkerFacade {

    private final MarkerService markerService;
    private final MarkerMapper markerMapper;

    public MarkerResponseDto findById(Long id) {
        Marker marker = markerService.findById(id);
        return markerMapper.toMarkerResponse(marker);
    }

    public List<MarkerResponseDto> findAll() {
        List<Marker> markers = markerService.findAll();

        return markers.stream().map(markerMapper::toMarkerResponse).toList();
    }

    public MarkerResponseDto save(CreateMarkerDto markerRequest) {
        Marker marker = markerMapper.toMarker(markerRequest);

        Marker savedMarker = markerService.save(marker);

        return markerMapper.toMarkerResponse(savedMarker);
    }

    public MarkerResponseDto update(UpdateMarkerDto markerRequest) {
        Marker marker = markerService.findById(markerRequest.getId());

        Marker updatedMarker = markerMapper.toMarker(markerRequest, marker);

        return markerMapper.toMarkerResponse(markerService.update(updatedMarker));
    }

    public void delete(Long id) {
        markerService.deleteById(id);
    }
}
