package com.example.offerdaysongs.service;

import com.example.offerdaysongs.dto.requests.CreateSingerRequest;
import com.example.offerdaysongs.exceptions.NoContentException;
import com.example.offerdaysongs.model.Singer;
import com.example.offerdaysongs.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SingerService
{
    private static final String NOT_FOUND = "Singer with id %d not found";
    private final SingerRepository singerRepository;

    @Autowired
    public SingerService(SingerRepository singerRepository)
    {
        this.singerRepository = singerRepository;
    }

    public List<Singer> getAllSingers()
    {
        return singerRepository.findAll();
    }

    public Singer getById(long id)
    {
        if (!singerRepository.existsById(id))
        {
            throw new NoContentException(String.format(NOT_FOUND, id));
        }
        return singerRepository.getById(id);
    }

    public Singer create(CreateSingerRequest request)
    {
        Singer singer = new Singer();
        singer.setName(request.getName());
        return singerRepository.save(singer);
    }

    public Singer update(long id, CreateSingerRequest request)
    {
        if (!singerRepository.existsById(id))
        {
            throw new NoContentException(String.format(NOT_FOUND, id));
        }
        var singer = new Singer();
        singer.setId(id);
        singer.setName(request.getName());
        return singerRepository.save(singer);
    }

    public Singer edit(long id, CreateSingerRequest request)
    {
        Optional<Singer> singer = singerRepository.findById(id);
        if (singer.isEmpty())
        {
            throw new NoContentException(String.format(NOT_FOUND, id));
        }
        var newSingerData = new Singer();
        newSingerData.setName(request.getName());
        singer.get().copyNonNullProperties(newSingerData);
        return singerRepository.save(singer.get());
    }

    public void delete(long id)
    {
        if (!singerRepository.existsById(id))
        {
            throw new NoContentException(String.format(NOT_FOUND, id));
        }
        singerRepository.deleteById(id);
    }
}
