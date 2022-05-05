package com.example.ais_cdo_university.controller;

import com.example.ais_cdo_university.Student;
import com.example.ais_cdo_university.models.*;
import com.example.ais_cdo_university.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class MainController {

    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static String ADMIN = "admin";
    private final static String STUDENT = "student";
    private final static String EMPLOYEE = "employee";

    private String password;
    private String login;

    private StudentEntity currentStudentEntity;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TypesOfUsersRepository typesOfUsersRepository;

    @Autowired
    private DatesStartEducationRepository datesStartEducationRepository;

    @Autowired
    private DatesEndEducationRepository datesEndEducationRepository;
    @Autowired
    private DomashniyZadanieRepository domashniyZadanieRepository;

    @Autowired
    private DopSpecialnostiRepository dopSpecialnostiRepository;

    @Autowired
    private FakultetsRepository fakultetsRepository;
    @Autowired
    private FioRepository fioRepository;

    @Autowired
    private PolRepository polRepository;

    @Autowired
    private GrupsNomerRepository grupsNomerRepository;

    @Autowired
    private KollichestvoBalovVSystemeUspechRepository kollichestvoBalovVSystemeUspechRepository;

    @Autowired
    private KursesRepository kursesRepository;

    @Autowired
    private RaspisaniyesRepository raspisaniyesRepository;

    @Autowired
    private OstatkiNaSchetuRepository ostatkiNaSchetuRepository;

    @Autowired
    private PhonesRepository phonesRepository;

    @Autowired
    private DostigeniaRepository dostigeniaRepository;

    @Autowired
    private DostigenieRepository dostigenieRepository;

    @Autowired
    private ZadolgenntostiStudentaRepository zadolgenntostiStudentaRepository;

    @Autowired
    private ZadolgennostRepository zadolgennostRepository;

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @PostMapping("/main/enter")
    public String enterButton(@RequestParam String login, @RequestParam String password, Model model) {
        User user = userRepository.findUserByLoginAndPassword(login, password);
        if (user != null) {
            this.login = login;
            this.password = password;
            TypeOfUser typeOfUser = typesOfUsersRepository.findByUser(user);

            if (typeOfUser.getType().matches(ADMIN)) {
                Iterable<StudentEntity> students = studentRepository.findAll();
                Iterable<User> users = userRepository.findAll();
                model.addAttribute("users", users);
                model.addAttribute("students", students);
                return "redirect:/main/admin";
            } else if (typeOfUser.getType().matches(EMPLOYEE)) {
                return "redirect:/main/employee/";
            } else if (typeOfUser.getType().matches(STUDENT)) {
                return "redirect:/main/student";
            }
        }
        model.addAttribute("notFind", "Пользователь не найден");
        return "main";
    }


    @GetMapping("/main/student")
    public String getStudentPage() {
        return "student/student";
    }

    @GetMapping("/main/student/viewSelfInfo/return")
    public String getStudentMenuPageIfOnViewSelfInfoPage() {
        return "redirect:/main/student";
    }

    @GetMapping("/main/student/viewEducationDept/return")
    public String getStudentMenuPageIfOnViewEducationDept() {
        return "redirect:/main/student";
    }

    @GetMapping("/main/student/ViewSchedulesAndHomework/return")
    public String getStudentMenuPageIfOnViewSchedulesAndHomework() {
        return "redirect:/main/student";
    }

    @PostMapping("/main/student/viewSelfInfo")
    public String viewSelfInfo(Model model) {
        User user = userRepository.findUserByLoginAndPassword(this.login, this.password);
        currentStudentEntity = studentRepository.findStudentByUser_id(user.getId());
        addStudentAtributeToPage(model);
        return "student/selfInfoStudent";
    }

    @PostMapping("/main/student/viewEducationDept")
    public String viewDept(Model model) {
        User user = userRepository.findUserByLoginAndPassword(this.login, this.password);
        StudentEntity studentEntity = studentRepository.findStudentByUser_id(user.getId());
        ZadolgenoostiStudenta zadolgenoostiStudenta = zadolgenntostiStudentaRepository.findZadolgenoostiStudentaByStudent(studentEntity);
        StringBuffer zadolgennsotString = new StringBuffer();
        for (Zadolgennost z:
        zadolgennostRepository.findZadolgennostsByStudent(zadolgenoostiStudenta)) {
            zadolgennsotString.append(z.getZadolgennostName()).append(", ");
        }
        model.addAttribute("dept", zadolgennsotString);
        return "student/educationDept";
    }

    @PostMapping("/main/student/viewSchedulesAndHomework")
    public String viewScheduleAndHomework(Model model) {
        User user = userRepository.findUserByLoginAndPassword(this.login, this.password);
        StudentEntity studentEntity = studentRepository.findStudentByUser_id(user.getId());
        model.addAttribute("raspisanie", raspisaniyesRepository.findRaspisaniyeByStudent(studentEntity).getRaspisanine());
        model.addAttribute("zadanie", domashniyZadanieRepository.findByStudentEntity(studentEntity).getDomashniyZadanie());
        return "student/schedulesAndHomework";
    }

    @GetMapping("/main/student/exit")
    public String exitStudent() {
        this.login = "";
        this.password = "";
        return "redirect:/main";
    }


    @PostMapping("/main/admin/findStudents/")
    public String findStudentsByCiterionsAdminPage(Model model, @RequestParam(required = false) String fio, @RequestParam(required = false) Integer kurs, @RequestParam(required = false) String fakultet, @RequestParam(required = false) String pol, @RequestParam(required = false) String dopSpecialnost) {
        model.addAttribute("students", makeParametersNullIfRequired(fio, kurs, fakultet, pol, dopSpecialnost));
        return "admin/findStudentsByCiterionsAdmin";
    }

    @GetMapping("/main/admin/exit")
    public String exitAdmin() {
        this.login = "";
        this.password = "";
        currentStudentEntity = null;
        return "redirect:/main";
    }

    @GetMapping("/main/admin")
    public String getAdminPage(Model model) {
        Iterable<StudentEntity> students = studentRepository.findAll();
        Iterable<User> users = userRepository.findAll();
        ArrayList<Student> studentArrayList = connectStudentProperties(students);
        model.addAttribute("students", studentArrayList);
        model.addAttribute("users", users);
        return "admin/admin";
    }

    @GetMapping("/main/admin/editInfoAboutStudent/exit")
    public String exitFromEditStudentDataPage() {
        return "redirect:/main/admin";
    }

    @GetMapping("/main/admin/findStudentsBycCiterions")
    public String findStudentsByCiterionsPage() {
        return "admin/findStudentsByCiterionsAdmin";
    }

    @GetMapping("/main/admin/findStudentsBycCiterions/exit")
    public String exitfindStudentsBycCiterionsPageAdmin() {
        return "redirect:/main/admin";
    }

    @GetMapping("/main/admin/functionSuccess/exit")
    public String exitFunctionSuccessPage() {
        return "redirect:/main/admin";
    }

    @PostMapping("/main/admin/editInfoAboutStudentPage")
    public String goToEditStudentDataPage(@RequestParam(required = false) Long id) {
        currentStudentEntity = studentRepository.findStudentById(id);
        return "admin/editInfoAboutStudent";
    }

    @PostMapping("/main/admin/editInfoAboutStudent/makeEdit")
    public String getEditInformationAboutStudent(@RequestParam(name = "fio") String fio, @RequestParam(name = "phone") String phone, @RequestParam(name = "kurs") Integer kurs, @RequestParam(name = "fakultet") String fakultet, @RequestParam(name = "pol") String pol, @RequestParam(name = "dataNachalaObuchenia") String dataNachalaObuchenia, @RequestParam(name = "dataOkonchaniaObuchenia") String dataOkonchaniaObuchenia, @RequestParam(name = "dopSpecialnost") String dopSpecialnost, @RequestParam(name = "groupOrWork") String groupOrWork, @RequestParam(name = "ostatokNaSchetu") Long ostatokNaSchetu, @RequestParam(name = "login") String login, @RequestParam(name = "password") String password) {
        User currentUser;
        TypeOfUser typeOfUser;
        DateStartEducation dateStartEducation;
        DatesEndEducation datesEndEducation;
        DomashniyZadanie domashniyZadanie;
        DopSpecialnosti dopSpecialnostEntity;
        Pol polEntity;
        Fio fioEntity;
        Fakultet fakultetEntity;
        GrupsNomer grupsNomerEntity;
        KollichestvoBalovVSystemeUspech kollichestvoBalovVSystemeUspechEntity;
        Kurs kursEntity;
        RaspisaniyeOfGroup raspisaniyeOfGroupEntity;
        OstatkiNaSchetu ostatkiNaSchetuEntity;
        Phone phoneEntity;
        Dostigenia listOfDostigenie;
        ZadolgenoostiStudenta zadolgenoostiStudenta;
        if (currentStudentEntity == null) {
            currentStudentEntity = new StudentEntity();
            currentUser = new User();
            typeOfUser = new TypeOfUser();
            dateStartEducation = new DateStartEducation();
            datesEndEducation = new DatesEndEducation();
            domashniyZadanie = new DomashniyZadanie();
            dopSpecialnostEntity = new DopSpecialnosti();
            fioEntity = new Fio();
            polEntity = new Pol();
            fakultetEntity = new Fakultet();

            grupsNomerEntity = new GrupsNomer();
            grupsNomerEntity.setGrupNomer(groupOrWork);
            grupsNomerRepository.save(grupsNomerEntity);

            kollichestvoBalovVSystemeUspechEntity = new KollichestvoBalovVSystemeUspech();
            kursEntity = new Kurs();
            raspisaniyeOfGroupEntity = new RaspisaniyeOfGroup();
            ostatkiNaSchetuEntity = new OstatkiNaSchetu();
            phoneEntity = new Phone();
            listOfDostigenie = new Dostigenia();
            zadolgenoostiStudenta = new ZadolgenoostiStudenta();
        } else {
            currentUser = userRepository.findUserById(currentStudentEntity.getUser().getId());
            typeOfUser = typesOfUsersRepository.findByUser(currentUser);
            dateStartEducation = datesStartEducationRepository.findByStudentEntity(currentStudentEntity);
            datesEndEducation = datesEndEducationRepository.findByStudentEntity(currentStudentEntity);
            domashniyZadanie = domashniyZadanieRepository.findByStudentEntity(currentStudentEntity);
            dopSpecialnostEntity = dopSpecialnostiRepository.findDopSpecialnostiByStudent(currentStudentEntity);
            fioEntity = fioRepository.findFioByStudent(currentStudentEntity);
            polEntity = polRepository.findPolByStudent(currentStudentEntity);
            fakultetEntity = fakultetsRepository.findFakultetByStudent(currentStudentEntity);
            kollichestvoBalovVSystemeUspechEntity = kollichestvoBalovVSystemeUspechRepository.findKollichestvoBalovVSystemeUspechByStudent(currentStudentEntity);
            kursEntity = kursesRepository.findKursByStudent(currentStudentEntity);
            raspisaniyeOfGroupEntity = raspisaniyesRepository.findRaspisaniyeByStudent(currentStudentEntity);
            ostatkiNaSchetuEntity = ostatkiNaSchetuRepository.findOstatkiNaSchetuByStudent(currentStudentEntity);
            phoneEntity = phonesRepository.findPhoneByStudent(currentStudentEntity);
            listOfDostigenie = dostigeniaRepository.findDostigeniaByStudent(currentStudentEntity);
            zadolgenoostiStudenta = zadolgenntostiStudentaRepository.findZadolgenoostiStudentaByStudent(currentStudentEntity);
        }

        typeOfUser.setType(STUDENT);
        typeOfUser.setUser(currentUser);
        currentUser.setPassword(password);
        currentUser.setLogin(login);
        userRepository.save(currentUser);
        typesOfUsersRepository.save(typeOfUser);

        currentStudentEntity.setUser(userRepository.findUserById(currentUser.getId()));
        currentStudentEntity.setGrupNomer(groupOrWork);
        studentRepository.save(currentStudentEntity);

        dateStartEducation.setDate(LocalDate.parse(dataNachalaObuchenia, dtf));
        dateStartEducation.setStudent(currentStudentEntity);
        datesStartEducationRepository.save(dateStartEducation);

        datesEndEducation.setDateEndEducation(LocalDate.parse(dataOkonchaniaObuchenia, dtf));
        datesEndEducation.setStudent(currentStudentEntity);
        datesEndEducationRepository.save(datesEndEducation);

        domashniyZadanie.setDomashniyZadanie("нет");
        domashniyZadanie.setStudent(currentStudentEntity);
        domashniyZadanieRepository.save(domashniyZadanie);

        dopSpecialnostEntity.setDopSpecialnost(dopSpecialnost);
        dopSpecialnostEntity.setStudent(currentStudentEntity);
        dopSpecialnostiRepository.save(dopSpecialnostEntity);

        fioEntity.setFio(fio);
        fioEntity.setStudent(currentStudentEntity);
        fioRepository.save(fioEntity);

        polEntity.setPol(pol);
        polEntity.setStudent(currentStudentEntity);
        polRepository.save(polEntity);

        fakultetEntity.setFakultet(fakultet);
        fakultetEntity.setStudent(currentStudentEntity);
        fakultetsRepository.save(fakultetEntity);

        kollichestvoBalovVSystemeUspechEntity.setKollichestovBalov(0);
        kollichestvoBalovVSystemeUspechEntity.setStudent(currentStudentEntity);
        kollichestvoBalovVSystemeUspechRepository.save(kollichestvoBalovVSystemeUspechEntity);

        kursEntity.setKurs(kurs);
        kursEntity.setStudent(currentStudentEntity);
        kursesRepository.save(kursEntity);

        raspisaniyeOfGroupEntity.setRaspisanine("нет");
        raspisaniyeOfGroupEntity.setStudent(currentStudentEntity);
        raspisaniyesRepository.save(raspisaniyeOfGroupEntity);

        ostatkiNaSchetuEntity.setOstatokNaSchetu(ostatokNaSchetu);
        ostatkiNaSchetuEntity.setStudent(currentStudentEntity);
        ostatkiNaSchetuRepository.save(ostatkiNaSchetuEntity);

        phoneEntity.setPhone(phone);
        phoneEntity.setStudent(currentStudentEntity);
        phonesRepository.save(phoneEntity);

        listOfDostigenie.setStudent(currentStudentEntity);
        dostigeniaRepository.save(listOfDostigenie);

        zadolgenoostiStudenta.setStudent(currentStudentEntity);
        zadolgenntostiStudentaRepository.save(zadolgenoostiStudenta);
        return "redirect:/main/admin";
    }

    @PostMapping("/main/admin/functionSuccessAdmin")
    public String functionSuccessPageAdmin(@RequestParam(name = "kurs", required = false) Integer kurs, Model model) {
        if (kurs != null) {
            functionSuccess(kurs, model);
            return "admin/functionSuccessAdmin";
        }
        return "redirect:/main/admin";
    }

    @PostMapping("/main/admin/editZadolgennosti")
    public String editZadolgennostiAdminPage(@RequestParam(name = "studentId") Long id, @RequestParam(name = "zadolgennost") String zadolgennost) {
        StudentEntity studentEntity = studentRepository.findStudentById(id);
        ZadolgenoostiStudenta zadolgenoostiStudenta = zadolgenntostiStudentaRepository.findZadolgenoostiStudentaByStudent(studentEntity);
        Zadolgennost newZadolgennost = new Zadolgennost();
        newZadolgennost.setZadolgennostName(zadolgennost);
        newZadolgennost.setStudent(zadolgenoostiStudenta);
        zadolgennostRepository.save(newZadolgennost);
        studentRepository.save(studentEntity);
        return "redirect:/main/admin";
    }

    @PostMapping("/main/admin/editRaspisanie")
    public String editRaspisanieAdminPage(@RequestParam(name = "groupNumber") String groupNumber, @RequestParam(name = "raspisanieGrupi") String raspisanieGrupi) {
        Iterable<RaspisaniyeOfGroup> studentsRaspisanie = raspisaniyesRepository.findRaspisaniyeOfGroupsByGrupsNomer(grupsNomerRepository.findGrupsNomersByGrupNomer(groupNumber));
        for (RaspisaniyeOfGroup element :
                studentsRaspisanie) {
            element.setRaspisanine(raspisanieGrupi);
            raspisaniyesRepository.save(element);
        }
        return "redirect:/main/admin";
    }

    @PostMapping("/main/admin/findCurrentStudent")
    public String findCurrentStudentAdmin(@RequestParam(name = "studentId") Long id, Model model) {
        if (id != null) {
            currentStudentEntity = studentRepository.findStudentById(id);
            if (currentStudentEntity != null) {
                addStudentAtributeToPage(model);
                return "admin/studentInfoAdmin";
            }
        }
        return "redirect:/main/admin";
    }

    @GetMapping("/main/admin/findCurrentStudent/return")
    public String returnFromStudentInfoPageAdmin() {
        return "redirect:/main/admin";
    }

    @PostMapping("/main/admin/editSuccessFunction")
    public String editSuccessFunctionAdmin(@RequestParam(name = "studentId") Long id, @RequestParam(name = "dostigeniaStudenta") String dostigeniaStudenta, @RequestParam(name = "baliStudenta") Integer baliStudenta) {
        successFunction(id, dostigeniaStudenta, baliStudenta);
        return "redirect:/main/admin";
    }

    private void successFunction(@RequestParam(name = "studentId") Long id, @RequestParam(name = "dostigeniaStudenta") String dostigeniaStudenta, @RequestParam(name = "baliStudenta") Integer baliStudenta) {
        StudentEntity studentEntity = studentRepository.findStudentById(id);
        Dostigenia listOfStudentDostigenies = dostigeniaRepository.findDostigeniaByStudent(studentEntity);
        Dostigenie dostigenie = new Dostigenie();

        dostigenie.setDostigenie(dostigeniaStudenta);
        dostigenie.setKollichestvoBalov(baliStudenta);
        dostigenie.setStudent(listOfStudentDostigenies);
        dostigenieRepository.save(dostigenie);

        KollichestvoBalovVSystemeUspech kollichestvoBalovVSystemeUspechEntity = kollichestvoBalovVSystemeUspechRepository.findKollichestvoBalovVSystemeUspechByStudent(studentEntity);
        kollichestvoBalovVSystemeUspechEntity.setKollichestovBalov(kollichestvoBalovVSystemeUspechEntity.getKollichestovBalov() + baliStudenta);
        kollichestvoBalovVSystemeUspechRepository.save(kollichestvoBalovVSystemeUspechEntity);
    }


    @PostMapping("/main/employee/findStudents/")
    public String findStudentsByCiterionsEmployeesPage(Model model, @RequestParam(required = false) String fio, @RequestParam(required = false) Integer kurs, @RequestParam(required = false) String fakultet, @RequestParam(required = false) String pol, @RequestParam(required = false) String dopSpecialnost) {
        model.addAttribute("student", makeParametersNullIfRequired(fio, kurs, fakultet, pol, dopSpecialnost));
        return "employee/findStudentsByCiterionsEmployee";
    }

    @GetMapping("/main/employee/")
    public String getEmployeePage(Model model) {
        Iterable<StudentEntity> buffer = studentRepository.findAll();
        HashMap<String, String> studentsHashMap = new HashMap<>();
        for (StudentEntity element :
                buffer) {
            studentsHashMap.put(element.getGrupNomer(), raspisaniyesRepository.findRaspisaniyeByStudent(element).getRaspisanine());
        }
        model.addAttribute("map", studentsHashMap);
        return "employee/employee";
    }

    @GetMapping("/main/employee/exit")
    public String exitEmployeePage() {
        this.login = null;
        this.password = null;
        return "redirect:/main";
    }

    @GetMapping("/main/employee/findStudentsBycCiterions/exit")
    public String exitfindStudentsBycCiterionsPageEmployee() {
        return "redirect:/main/employee/";
    }

    @PostMapping("/main/employee/functionSuccess")
    public String functionSuccessPageEmployee(@RequestParam(name = "kurs", required = false) Integer kurs, Model model) {
        if (kurs != null) {
            functionSuccess(kurs, model);
            return "employee/functionSuccessEmployee";
        }
        return "redirect:/main/employee";
    }

    @GetMapping("/main/employee/functionSuccessEmployee/exit")
    public String exitFunctionSuccessPageEmployeeAdmin() {
        return "redirect:/main/employee/";
    }

    @PostMapping("/main/employee/editSuccessFunction")
    public String editSuccessFunctionEmployee(@RequestParam(name = "studentId") Long id, @RequestParam(name = "dostigeniaStudenta") String dostigeniaStudenta, @RequestParam(name = "baliStudenta") Integer baliStudenta) {
        successFunction(id, dostigeniaStudenta, baliStudenta);
        return "redirect:/main/employee/";
    }

    @PostMapping("/main/employee/findCurrentStudent")
    public String findCurrentStudentEmployee(@RequestParam(name = "studentId") Long id, Model model) {
        if (id != null) {
            currentStudentEntity = studentRepository.findStudentById(id);
            if (currentStudentEntity != null) {
                addStudentAtributeToPage(model);
                return "employee/studentInfoEmployee";
            }
        }
        return "redirect:/main/employee/";
    }

    @GetMapping("/main/employee/findCurrentStudentEmployee/return")
    public String returnFromStudentInfoPageEmployee() {
        return "redirect:/main/employee/";
    }

    @PostMapping("/main/employee/editHomework")
    public String addHomework(@RequestParam(name = "group") String group, @RequestParam(name = "homework") String homework) {

        Iterable<DomashniyZadanie> studentsDZ = domashniyZadanieRepository.findAll();
        for (DomashniyZadanie studentDZ :
                studentsDZ) {
            if (grupsNomerRepository.findGrupsNomersByGrupNomer(group).getGrupNomer().matches(group)) {
                studentDZ.setDomashniyZadanie(studentDZ.getDomashniyZadanie() + " : " + homework);
            }
        }
        domashniyZadanieRepository.saveAll(studentsDZ);
        return "redirect:/main/employee/";
    }

    private ArrayList<Student> makeParametersNullIfRequired(@RequestParam(required = false) String fio, @RequestParam(required = false) Integer kurs, @RequestParam(required = false) String fakultet, @RequestParam(required = false) String pol, @RequestParam(required = false) String dopSpecialnost) {
        if (fio.matches("")) {
            fio = null;
        }
        if (pol.matches("")) {
            pol = null;
        }
        if (fakultet.matches("")) {
            fakultet = null;
        }
        if (dopSpecialnost.matches("")) {
            dopSpecialnost = null;
        }
        Iterable<StudentEntity> students = studentRepository.findByStudentEntity(fio, pol, fakultet, kurs, dopSpecialnost);
        return connectStudentProperties(students);
    }

    private void addStudentAtributeToPage(Model model) {
        StringBuffer spisocDostigeniyString = new StringBuffer();
        for (Dostigenie d:
                dostigenieRepository.findDostigeniesByStudent(dostigeniaRepository.findDostigeniaByStudent(currentStudentEntity))) {
            spisocDostigeniyString.append(d.getDostigenie()).append(", ");
        }
        StringBuffer zadolgennostiString = new StringBuffer();
        for (Zadolgennost z:
                zadolgennostRepository.findZadolgennostsByStudent(zadolgenntostiStudentaRepository.findZadolgenoostiStudentaByStudent(currentStudentEntity))) {
            spisocDostigeniyString.append(z.getZadolgennostName()).append(", ");
        }
        model.addAttribute("FIO", fioRepository.findFioByStudent(currentStudentEntity).getFio());
        model.addAttribute("Pol", polRepository.findPolByStudent(currentStudentEntity).getPol());
        model.addAttribute("Fakultet", fakultetsRepository.findFakultetByStudent(currentStudentEntity).getFakultet());
        model.addAttribute("Kurs", kursesRepository.findKursByStudent(currentStudentEntity).getKurs());
        model.addAttribute("DopSpecialnost", dopSpecialnostiRepository.findDopSpecialnostiByStudent(currentStudentEntity).getDopSpecialnost());
        model.addAttribute("OstatokNaSchetu", ostatkiNaSchetuRepository.findOstatkiNaSchetuByStudent(currentStudentEntity).getOstatokNaSchetu());
        model.addAttribute("Zadolgensti", zadolgennostiString);
        model.addAttribute("SpisocDostigeniy", spisocDostigeniyString);
        model.addAttribute("kolichestvoBalov", kollichestvoBalovVSystemeUspechRepository.findKollichestvoBalovVSystemeUspechByStudent(currentStudentEntity).getKollichestovBalov());
        model.addAttribute("GruppNomer", currentStudentEntity.getGrupNomer());
        model.addAttribute("Raspisanie", raspisaniyesRepository.findRaspisaniyeByStudent(currentStudentEntity).getRaspisanine());
        model.addAttribute("DataNachalaObuchenia", datesStartEducationRepository.findByStudentEntity(currentStudentEntity).getDate());
        model.addAttribute("DataOkonchaniaObuchenia", datesEndEducationRepository.findByStudentEntity(currentStudentEntity).getDateEndEducation());
    }

    private ArrayList<Student> connectStudentProperties(Iterable<StudentEntity> students) {
        ArrayList<Student> studentArrayList = new ArrayList<>();
        for (StudentEntity student :
                students) {
            connectProperties(studentArrayList, student);
        }
        return studentArrayList;
    }

    private void functionSuccess(@RequestParam(name = "kurs", required = false) Integer kurs, Model model) {
        Iterable<Kurs> students = kursesRepository.findKursByKurs(kurs);
        ArrayList<Student> studentArrayList = new ArrayList<>();
        StudentEntity student;
        for (Kurs kursEntry :
                students) {
            student = studentRepository.findStudentById(kursEntry.getStudent().getId());
            connectProperties(studentArrayList, student);
        }
        model.addAttribute("students", studentArrayList);
    }

    private void connectProperties(ArrayList<Student> studentArrayList, StudentEntity student) {
        studentArrayList.add(new Student(
                student.getId(), student.getUser(), fioRepository.findFioByStudent(student).getFio(), phonesRepository.findPhoneByStudent(student).getPhone(), kursesRepository.findKursByStudent(student).getKurs(), dopSpecialnostiRepository.findDopSpecialnostiByStudent(student).getDopSpecialnost(), ostatkiNaSchetuRepository.findOstatkiNaSchetuByStudent(student).getOstatokNaSchetu(), fakultetsRepository.findFakultetByStudent(student).getFakultet(), zadolgennostRepository.findZadolgennostsByStudent(zadolgenntostiStudentaRepository.findZadolgenoostiStudentaByStudent(student)), dostigenieRepository.findDostigeniesByStudent(dostigeniaRepository.findDostigeniaByStudent(student)), student.getGrupNomer(), polRepository.findPolByStudent(student).getPol(), raspisaniyesRepository.findRaspisaniyeByStudent(student).getRaspisanine(), kollichestvoBalovVSystemeUspechRepository.findKollichestvoBalovVSystemeUspechByStudent(student).getKollichestovBalov(), datesEndEducationRepository.findByStudentEntity(student).getDateEndEducation(), datesStartEducationRepository.findByStudentEntity(student).getDate(), domashniyZadanieRepository.findByStudentEntity(student).getDomashniyZadanie()));
    }

}
