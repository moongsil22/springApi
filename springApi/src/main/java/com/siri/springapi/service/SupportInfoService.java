package com.siri.springapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siri.springapi.domain.SupportInfo;
import com.siri.springapi.domain.SupportInfoRepository;
import com.siri.springapi.domain.SupportReg;
import com.siri.springapi.domain.SupportRegRepository;
import com.siri.springapi.dto.SupportReqDto;

@Service
public class SupportInfoService {
	
	private SupportInfoRepository supportInfoRepository;
	private SupportRegRepository supportRegRepository;
	
    public SupportInfoService(SupportInfoRepository supportInfoRepository) {
        this.supportInfoRepository = supportInfoRepository;
    }

    @Transactional
    public SupportInfo saveInfo(SupportInfo input) {
        return supportInfoRepository.save(input);
    }
    
    @Transactional
    public SupportReg saveReg(SupportReg input) {
        return supportRegRepository.save(input);
    }
    
    /*
    public Optional<SupportInfo> getSupportInfo(Long id) {
        return supportInfoRepository.findById(id);
    }
    
    public SupportReqDto getSupportAll() {
        return (SupportReqDto) supportInfoRepository.findAll();
    }*/

}
