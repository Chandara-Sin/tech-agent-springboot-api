package com.scd.tech_agent.service;

import com.scd.tech_agent.entity.Position;
import com.scd.tech_agent.exception.DataInvalid;
import com.scd.tech_agent.exception.DataNotFound;
import com.scd.tech_agent.repository.DepartmentRepository;
import com.scd.tech_agent.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PositionService {

    @Autowired
    PositionRepository positionRepo;

    @Autowired
    DepartmentRepository departmentRepo;

    public List<Position> getPositionList() {
        List<Position> positionList = positionRepo.findAll();
        if (positionList.isEmpty()) {
            throw new RuntimeException("Some error occurred while retrieving Positions");
        } else return positionList;
    }

    public List<Position> getPositionListByDepartment(Integer deptId) {
        List<Position> positionList = positionRepo.findAllByDeptId(deptId);
        if (positionList.isEmpty()) {
            throw new DataNotFound("Positions Not Found with this department id : " + deptId);
        } else return positionList;
    }

    public Position addPosition(Position dataRequest) {
        Position position = new Position();
        position.setPosition(dataRequest.getPosition());
        position.setDeptId(dataRequest.getDeptId());
        position.setCreatedAt(LocalDateTime.now());
        position.setUpdatedAt(LocalDateTime.now());
        try {
            return positionRepo.save(position);
        } catch (RuntimeException e) {
            throw new RuntimeException("Some error occurred while creating the Position");
        }
    }

    public Position updatePosition(Integer postnId, Position dataRequest) {
        Position position = positionRepo.findById(postnId)
                .orElseThrow(() -> new DataNotFound("Position not found for this id : " + postnId));

        if (positionRepo.existsByPosition(dataRequest.getPosition()))
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
        Position position = positionRepo.findById(postnId).orElseThrow(
                () -> new DataNotFound("Position not found for this id : " + postnId));

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
