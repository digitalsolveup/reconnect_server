package reconnect.server.domain.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reconnect.server.domain.report.model.request.ReportRequest;
import reconnect.server.domain.report.model.response.ReportResponse;
import reconnect.server.domain.report.repository.ReportRepository;
import reconnect.server.domain.missing_person.repository.MissingPersonRepository;
import reconnect.server.global.model.entity.mysql.MissingPerson;
import reconnect.server.global.model.entity.mysql.Report;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final MissingPersonRepository missingPersonRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository, MissingPersonRepository missingPersonRepository) {
        this.reportRepository = reportRepository;
        this.missingPersonRepository = missingPersonRepository;
    }

    // 제보 등록
    public ReportResponse createReport(ReportRequest request) {
        // MissingPerson 객체를 MissingPersonRepository를 통해 조회
        MissingPerson missingPerson = missingPersonRepository.findById(request.getMissingPersonId())
                .orElseThrow(() -> new IllegalArgumentException("MissingPerson not found with id: " + request.getMissingPersonId()));

        // Report 객체 생성
        Report report = Report.builder()
                .missingPerson(missingPerson)
                .gender(request.getGender())
                .ageGroup(request.getAgeGroup())
                .specialFeature(request.getSpecialFeature())
                .tops(request.isTops())
                .bottoms(request.isBottoms())
                .shoes(request.isShoes())
                .accessories(request.isAccessories())
                .hair(request.isHair())
                .foundImageUrls(request.getFoundImageUrls())
                .locationFound(request.getLocationFound())
                .foundLatitude(request.getFoundLatitude())
                .foundLongitude(request.getFoundLongitude())
                .additionalDescription(request.getAdditionalDescription())
                .surroundingImageUrls(request.getSurroundingImageUrls())
                .additionalReport(request.getAdditionalReport())
                .reportedAt(LocalDateTime.now())
                .build();

        // 제보 저장
        report = reportRepository.save(report);

        // 실종자의 reportCount 증가
        missingPerson.setReportCount(missingPerson.getReportCount() + 1);
        missingPersonRepository.save(missingPerson);

        return new ReportResponse(report);
    }

    // 제보 수정
    public ReportResponse updateReport(Long reportId, ReportRequest request) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("Report not found"));

        report.setGender(request.getGender());
        report.setAgeGroup(request.getAgeGroup());
        report.setSpecialFeature(request.getSpecialFeature());
        report.setTops(request.isTops());
        report.setBottoms(request.isBottoms());
        report.setShoes(request.isShoes());
        report.setAccessories(request.isAccessories());
        report.setHair(request.isHair());
        report.setFoundImageUrls(request.getFoundImageUrls());
        report.setLocationFound(request.getLocationFound());
        report.setFoundLatitude(request.getFoundLatitude());
        report.setFoundLongitude(request.getFoundLongitude());
        report.setAdditionalDescription(request.getAdditionalDescription());
        report.setSurroundingImageUrls(request.getSurroundingImageUrls());
        report.setAdditionalReport(request.getAdditionalReport());

        report = reportRepository.save(report);
        return new ReportResponse(report);
    }

    // 나의 제보 목록 조회
    public List<ReportResponse> getMyReports() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream().map(ReportResponse::new).collect(Collectors.toList());
    }
}
