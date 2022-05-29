package com.scd.tech_agent.service;

import com.scd.tech_agent.entity.Position;
import com.scd.tech_agent.exception.DataInvalid;
import com.scd.tech_agent.exception.DataNotFound;
import com.scd.tech_agent.repository.DepartmentRepository;
import com.scd.tech_agent.repository.PositionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public record PositionService(PositionRepository positionRepo, DepartmentRepository departmentRepo) {

    public List<Position> getPositionList() {
        List<Position> positionList;
        try {
            positionList = positionRepo.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException("Some error occurred while retrieving Positions");
        }
        if (positionList.isEmpty())
            throw new DataNotFound("Found no Positions in Database");
        return positionList;
    }

    public List<Position> getPositionListByDepartment(Integer deptId) {
        List<Position> positionList;
        try {
            positionList = positionRepo.findAllByDeptId(deptId);
        } catch (RuntimeException e) {
            throw new RuntimeException("Some error occurred while retrieving Positions");
        }
        if (positionList.isEmpty())
            throw new DataNotFound("Positions Not Found with this department id : " + deptId);
        return positionList;
    }

    public Position addPosition(Position dataRequest) {
        if (positionRepo.existsByPosition(dataRequest.getPosition()))
            throw new DataInvalid("duplicate position");

        dataRequest.setCreatedAt(LocalDateTime.now());
        dataRequest.setUpdatedAt(LocalDateTime.now());
        try {
            return positionRepo.save(dataRequest);
        } catch (RuntimeException e) {
            throw new RuntimeException("Some error occurred while creating the Position");
        }
    }

    public Position updatePosition(Integer postnId, Position dataRequest) {
        Position position = positionRepo.findById(postnId)
                .orElseThrow(() -> new DataNotFound("Position not found for this id : " + postnId));

        if (positionRepo.existsByPositionAndIdNot(dataRequest.getPosition(), postnId))
            throw new DataInvalid("duplicate position name");

        if (!departmentRepo.existsById(dataRequest.getDeptId()))
            throw new DataInvalid("Department not exist");

        position.setPosition(dataRequest.getPosition());
        position.setCreatedAt(position.getCreatedAt());
        position.setUpdatedAt(LocalDateTime.now());
        position.setDeptId(dataRequest.getDeptId());

        try {
            return positionRepo.save(position);
        } catch (RuntimeException e) {
            throw new RuntimeException("Some error occurred while updating the position");
        }
    }

    public Map<String, String> deletePosition(Integer postnId) {
        Position position = positionRepo.findById(postnId)
                .orElseThrow(() -> new DataNotFound("Position not found for this id : " + postnId));

        try {
            positionRepo.delete(position);
        } catch (RuntimeException e) {
            throw new RuntimeException("Could not delete Position with id : " + postnId);
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Position was deleted successfully");
        response.put("status", HttpStatus.OK.toString());
        return response;
    }
}
