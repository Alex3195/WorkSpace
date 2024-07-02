package uz.alex.workspace.service;

import uz.alex.workspace.model.PositionModel;

import java.util.List;

public interface PositionsService {
    List<PositionModel> getPositions();

    PositionModel getPosition(int id);

    PositionModel createPosition(PositionModel position);

    PositionModel updatePosition(PositionModel position);

    void deletePosition(int id);

}
