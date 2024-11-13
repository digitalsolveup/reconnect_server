package reconnect.server.domain.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reconnect.server.domain.report.model.request.ReportRequest;
import reconnect.server.domain.report.model.response.ReportResponse;
import reconnect.server.domain.report.repository.ReportRepository;
import reconnect.server.global.model.entity.mysql.MissingPerson;
import reconnect.server.global.model.entity.mysql.Report;
import reconnect.server.domain.missing_person.repository.MissingPersonRepository;

import java.util.Optional;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final MissingPersonRepository missingPersonRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository, MissingPersonRepository missingPersonRepository) {
        this.reportRepository = reportRepository;
        this.missingPersonRepository = missingPersonRepository;
    }

    // 제보 화면 실종자 정보 조회
    public Object[] getMissingPersonInfo(Long missingPersonId) {
        return reportRepository.findMissingPersonInfoById(missingPersonId);
    }

    // 제보 생성
    public ReportResponse createReport(ReportRequest reportRequest) {
        MissingPerson missingPerson = missingPersonRepository.findById(reportRequest.getMissingPersonId())
                .orElseThrow(() -> new RuntimeException("MissingPerson not found"));

        // Report 엔티티 생성 및 저장
        Report report = mapToEntity(reportRequest, missingPerson);
        reportRepository.save(report);

        // reportCount 업데이트
        missingPerson.setReportCount(missingPerson.getReportCount() + 1);
        missingPersonRepository.save(missingPerson);

        return mapToResponse(report);
    }

    private Report mapToEntity(ReportRequest request, MissingPerson missingPerson) {
        return Report.builder()
                .missingPerson(missingPerson)
                .gender(request.getGender())
                .ageGroup(request.getAgeGroup())
                .specialFeature(request.getSpecialFeature())
                .clothingItems(request.getClothingItems())
                .foundImageUrl(request.getFoundImageUrl())
                .locationFound(request.getLocationFound())
                .additionalDescription(request.getAdditionalDescription())
                .surroundingImageUrl(request.getSurroundingImageUrl())
                .additionalReport(request.getAdditionalReport())
                .reportCount(missingPerson.getReportCount() + 1)
                .build();
    }

    private ReportResponse mapToResponse(Report report) {
        return ReportResponse.builder()
                .reportId(report.getReportId())
                .missingPersonId(report.getMissingPerson().getId())
                .gender(report.getGender())
                .ageGroup(report.getAgeGroup())
                .specialFeature(report.getSpecialFeature())
                .clothingItems(report.getClothingItems())
                .foundImageUrl(report.getFoundImageUrl())
                .locationFound(report.getLocationFound())
                .additionalDescription(report.getAdditionalDescription())
                .surroundingImageUrl(report.getSurroundingImageUrl())
                .additionalReport(report.getAdditionalReport())
                .reportCount(report.getReportCount())
                .build();
    }
}
