package com.revature.Services;
import com.revature.Model.Owner;
import com.revature.Model.Team;
import com.revature.Repos.OwnerRepository;
import com.revature.Repos.TeamsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServImp implements OwnerService{
    @Autowired
    OwnerRepository or;

    @Override
    public Owner addConnection(Owner own) {
        return or.save(own);
    }

    @Override
    public Owner getById(int id)
    {
        Optional<Owner> test = or.findById(id);
        return test.isPresent() ? test.get() : null;
    }

    @Override
    public boolean deleteConnection(int id) {
        try {
            or.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteByUserTeamId(int uid, int tid) {
        Owner result = or.findAllByIds(uid, tid);
        try {
            or.delete(result);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
