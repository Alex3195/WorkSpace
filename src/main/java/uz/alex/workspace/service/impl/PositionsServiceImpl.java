package uz.alex.workspace.service.impl;

import org.springframework.stereotype.Service;
import uz.alex.workspace.model.PositionModel;
import uz.alex.workspace.repositories.PositionsRepository;
import uz.alex.workspace.service.PositionsService;

import java.util.List;

@Service
public class PositionsServiceImpl implements PositionsService {
    private final PositionsRepository positionsRepository;

    public PositionsServiceImpl(PositionsRepository positionsRepository) {
        this.positionsRepository = positionsRepository;
    }

    @Override
    public List<PositionModel> getPositions() {
        return List.of();
    }

    @Override
    public PositionModel getPosition(int id) {
        return null;
    }

    @Override
    public PositionModel createPosition(PositionModel position) {
        return null;
    }

    @Override
    public PositionModel updatePosition(PositionModel position) {
        return null;
    }

    @Override
    public void deletePosition(int id) {

    }
}
