package com.parking.app.controller;

import com.parking.app.domain.*;
import com.parking.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Controller    // This means that this class is a Controller
@RequestMapping(path="/") // This means URL's start with /demo (after Application path)
public class MainController {
    public static Integer myMonth = 0;


//    @Autowired
//    private DayScheduleRepository dayScheduleRepository;
//
//
//
//
//    @GetMapping(path="/addDays") // Map ONLY GET Requests
//    public @ResponseBody String addNewDaySchedule (@RequestParam String email,
//                                                   @RequestParam Integer parkingSpot,
//                                                   @RequestParam
//
//                                                               String endHour
//                                                  ) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        SimpleDateFormat formatter = new SimpleDateFormat("kk:mm:ss");
//        DaySchedule n = new DaySchedule();
//        n.setUserEmail(email);
//
//        n.setDate(new Date());
//        n.setStartHour(new Date());
////        n.setEndHour(LocalTime.parse(endHour));
//
//        try {
//            n.setEndHour(formatter.parse(endHour));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        n.setParkingSpot(parkingSpot);
//
//        dayScheduleRepository.save(n);
//        return "Saved days";
//    }
//    @GetMapping(path="/allDays")
//    public @ResponseBody
//    Iterable<DaySchedule> getAllDaySchedule() {
//        // This returns a JSON or XML with the users
//        return dayScheduleRepository.findAll();
//    }
//
//
//
//
//    @PostMapping(path="/jsonpost") // Map ONLY GET Requests
//    public @ResponseBody
//    String addNewDaySchedule (@RequestBody DaySchedule daySchedule
//    ) {
//        dayScheduleRepository.save(daySchedule);
//        return "created";
//    }
//
//    @PutMapping(path="/jsonput") // Map ONLY GET Requests
//    public @ResponseBody
//    ResponseEntity< String > modifyNewDaySchedule (@RequestBody DaySchedule daySchedule
//    ) {
//        dayScheduleRepository.save(daySchedule);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @DeleteMapping(value = "/delete/{id}")
//    public @ResponseBody
//    ResponseEntity<Object> deleteNewDaySchedule (@PathVariable("id") Long id
//    ) {
//        dayScheduleRepository.deleteById(id);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//
//    }
//
//
//    @DeleteMapping(value = "/deleteemail/{email}")
//    @Transactional
//    public @ResponseBody
//    ResponseEntity<Object> deleteNewDaySchedule (@PathVariable("email") String email
//    ) {
//        dayScheduleRepository.deleteByUserEmail(email);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//
//    }
//
//
//    @GetMapping(path="/allDays/{id}")
//    public @ResponseBody
//    Optional<DaySchedule> getDaySchedule(@PathVariable Long id) {
//        // This returns a JSON or XML with the users
//
//
//        return dayScheduleRepository.findById(id);
//    }

    @Autowired
    DayScheduleService dayScheduleService;



//    @GetMapping(path = "/allDaysService/{id}")
//    public @ResponseBody
//    DaySchedule getDayServiceSchedule(@PathVariable Long id) {
//        // This returns a JSON or XML with the users
//
//
//        return dayScheduleService.findById(id);
//    }


    @Autowired
    UserService userService;

    @GetMapping(path = "/register")
    public String registerRedirect() {
        return "register.html";
    }

//    @PostMapping(path = "/register")
//    public
//    String registerUser(User user,Model model) {
//        model.addAttribute("user", user);
//
//
//        if (userService.findByUserEmail(user.getUserEmail())==null) {
//
//            userService.saveUserToDb(user);
//            return "redirect:/parking";
//        }
//        else return "redirect:/register";
//    }

    @PostMapping(path = "/register")
    public void registerUser(HttpServletRequest request, HttpServletResponse response, UserHelper userHelper, Model model) throws IOException {
        model.addAttribute("userHelper", userHelper);
        User user = new User();
        String userEmail = userHelper.getUserEmail();
        user.setUserPassword(userHelper.getUserPassword());
        user.setUserEmail(userEmail);
        User userDB = null;
        userDB = userService.findByUserEmail(userEmail);
        if (userDB == null) {
            System.out.println("Fac salvarea in baza");
            userService.saveUserToDb(user);
            response.sendRedirect(request.getContextPath() + "/index.html");
        } else response.sendRedirect(request.getContextPath() + "/register.html");

    }

    @Autowired
    HistoryService historyService;

    @Autowired
    CheckOutHelperService checkOutHelperService;

    @GetMapping(path = "/checkout")
    public String checkoutGet() {
        //model.addAttribute("checkin", new GetHelper());
        return "checkout.html";
    }

    @PostMapping(path = "/checkout")
    public void checkOutUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getCookies() == null || request.getCookies().length == 0) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            // return "login";
        }
        Cookie cookie = request.getCookies()[0];
        String cookieVal = cookie.getValue();
        String email = userService.findUserByCookie(cookieVal).getUserEmail();

        checkOutHelperService.checkOut(email);
//        cookie = null;
//        User olduser = userService.findUserByCookie(cookieVal);
//        olduser.setCookie(null);
//        userService.saveUserToDb(olduser);
        response.sendRedirect(request.getContextPath() + "/parking.html");
        //return "parking";
    }


    @GetMapping("/")
    public String checkGet() {
        //model.addAttribute("checkin", new GetHelper());
        return "redirect:/index.html";
    }


//    @PutMapping(path = "/login")
//    public @ResponseBody
//    String loginInput(@RequestBody User user) {
//
//
//        if (userService.verifyUser(user)) return "logat";
//        else return "userul nu corespunde";
//    }


    @GetMapping(path = "/user/{userEmail}")
    public @ResponseBody
    User getUser(@PathVariable("userEmail") String userEmail) {
        // This returns a JSON or XML with the users


        return userService.findByUserEmail(userEmail);
    }


    @Autowired
    ParkingPackageHelper parkingPackageHelper;

    @GetMapping(path = "/parkingSpots")
    public @ResponseBody
    List<ParkingPackage> getAllParkingSpots() {
        // This returns a JSON or XML with the users
        return parkingPackageHelper.packForParking();
    }

    @PostMapping(path = "/parkingadmin")
    public void postParkingAdmin(HttpServletRequest request, HttpServletResponse response, @RequestBody ParkingHelper parkingHelper) throws IOException {

        if (request.getCookies() == null || request.getCookies().length == 0) {
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
        Cookie cookie = request.getCookies()[0];
        if (cookie.getValue().equals("AC75F6232AA3C5BE99ED53BADAD3C0055AFD84223DA52D38C4A691A785567E07".toLowerCase())) {
            Integer id = parkingHelper.getParkingSpotId();
            Integer status = parkingSpotService.findParkingSpotByParkingSpotId(id).getStatus();
            if (status == 0)
                parkingSpotService.setParkingSpotUnavailable(parkingHelper.getParkingSpotId());
            else if (status == 2) {
                parkingSpotService.setParkingSpotFree(parkingHelper.getParkingSpotId());
                checkOutHelperService.checkOut(userService.findUserByCookie(cookie.getValue()).getUserEmail());
            }

        }
    }

    @Autowired
    CheckInHelperService checkInHelperService;

    @PostMapping(path = "/parking")
    public void postParking(HttpServletRequest request, HttpServletResponse response, @RequestBody ParkingHelper parkingHelper) throws IOException {
        // This returns a JSON or XML with the users

//        //incep baoal
//        if(request.getCookies()==null || request.getCookies().length==0){
//            response.sendRedirect(request.getContextPath() + "/login");
//        }
//        Cookie cookie = request.getCookies()[0];
//        if(cookie.getValue().equals("AC75F6232AA3C5BE99ED53BADAD3C0055AFD84223DA52D38C4A691A785567E07".toLowerCase())){
//            Integer id = parkingHelper.getParkingSpotId();
//            Integer status = parkingSpotService.findParkingSpotByParkingSpotId(id).getStatus();
//            if (status == 0)
//                parkingSpotService.setParkingSpotUnavailable(parkingHelper.getParkingSpotId());
//            else {
//                parkingSpotService.setParkingSpotFree(parkingHelper.getParkingSpotId());
//                checkOutHelperService.checkOut(userService.findUserByCookie(cookie.getValue()).getUserEmail());
//            }
//
//        }
//
//        else {
//
//
//            //onchid boala

        Cookie cookie = request.getCookies()[0];
        if (request.getCookies() == null || request.getCookies().length == 0) {
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
        cookie = request.getCookies()[0];
        String cookieVal = cookie.getValue();
        String email = userService.findUserByCookie(cookieVal).getUserEmail();

        if (userService.findUserByCookie(cookieVal) != null) {
            CheckIn checkIn = new CheckIn();
            checkIn.setEndHour(parkingHelper.getEndHour());
            checkIn.setParkingSpotId(parkingHelper.getParkingSpotId());
            checkIn.setUserEmail(email);


            checkInHelperService.checkIn1(checkIn);
        }


    }


    @GetMapping(path = "/statistics")
    public @ResponseBody
    List<History> getAllHistoryFromMonth() {
        // This returns a JSON or XML with the users
        List<History> myList = new ArrayList();
        List<History> oldList = historyService.findAll();
        String luna = new DateFormatSymbols().getMonths()[myMonth];
        for (History history : oldList) {
            if (history.getDate().getMonth().toString().toLowerCase().equals(luna.toLowerCase())) {
                myList.add(history);
            }
        }
        return myList;
    }

    @PostMapping(path = "/statistic.html")
    public void postStatistics(HttpServletRequest request, HttpServletResponse response, @RequestBody MonthId month) throws IOException {
        myMonth = month.getMonth() - 1;

    }


//    @RequestMapping(value = "/showCheckIn", method=RequestMethod.GET)
//    public String showForm(Model model) {
//        GetHelper getHelper = new GetHelper();
//        getHelper.set
//
//        model.addAttribute("foo", foo);
//  ...
//    }
//
//    @RequestMapping(value = "/processForm", method=RequestMethod.POST)
//    public String processForm(@ModelAttribute(value="foo") Foo foo) {
//  ...
//    }

    @Autowired
    CheckInHelperService getHelperService;

    @GetMapping(path = "/form")
    public String checkinGet() {
        //model.addAttribute("checkin", new GetHelper());
        return "form";
    }


    @PostMapping(path = "/form")
    public String checkinPost(HttpServletResponse response, GetHelper getHelper, Model model) {
        model.addAttribute("getHelper", getHelper);

        response.addCookie(getHelperService.checkin(getHelper));

        return "form";
    }

    @GetMapping(path = "/login")
    public String loginGet(HttpServletRequest request) {

        if (request.getCookies() == null || request.getCookies().length == 0) {
            return "login";
        }
        return request.getContextPath() + "redirect:/parking.html";
    }

    @PostMapping(path = "/login")
    public void loginUser(HttpServletRequest request, HttpServletResponse response, UserHelper userHelper, Model model) throws IOException {
        model.addAttribute("userHelper", userHelper);
        User user = new User();
        String userEmail = userHelper.getUserEmail();
        user.setUserPassword(userHelper.getUserPassword());
        user.setUserEmail(userEmail);
        Cookie cookie = userService.logIn(user);
        if (cookie != null) {
            response.addCookie(cookie);
            if (userEmail.equals("floroiu.cristina@gmail.com"))
                response.sendRedirect(request.getContextPath() + "/parkingadmin.html");
            else if (dayScheduleService.findByUserEmail(userEmail) != null)
                response.sendRedirect(request.getContextPath() + "/checkout.html");


            else
                response.sendRedirect(request.getContextPath() + "/parking.html");
            //return "parking";
        } else
            response.sendRedirect(request.getContextPath() + "/login");
    }

//    @GetMapping(path = "/checkout")
//    public @ResponseBody
//    void checkOutUser(@RequestParam String userEmail) {
//
//
//        checkOutHelperService.checkOut(userEmail);
//    }


    @Autowired
    ParkingSpotService parkingSpotService;


    @GetMapping(path = "/admin/makeunavailable/{parkingSpotId}")
    @ResponseBody
    public HttpStatus makeParkingSpotUnavailable(HttpServletRequest request, @PathVariable("parkingSpotId") Integer parkingSpotId) {
        if (request.getCookies() == null || request.getCookies().length == 0) {
            return HttpStatus.FORBIDDEN;
        }
        Cookie cookie = request.getCookies()[0];
        if (!cookie.getValue().equals("AC75F6232AA3C5BE99ED53BADAD3C0055AFD84223DA52D38C4A691A785567E07".toLowerCase())) {
            return HttpStatus.BAD_REQUEST;
        }
        parkingSpotService.setParkingSpotUnavailable(parkingSpotId);

        return HttpStatus.OK;

    }


    @GetMapping(path = "/admin/makeavailable/{parkingSpotId}")
    @ResponseBody
    public HttpStatus makeParkingSpotAvailable(HttpServletRequest request, @PathVariable("parkingSpotId") Integer parkingSpotId) {

        if (request.getCookies() == null || request.getCookies().length == 0) {
            return HttpStatus.FORBIDDEN;
        }
        Cookie cookie = request.getCookies()[0];
        if (!cookie.getValue().equals("AC75F6232AA3C5BE99ED53BADAD3C0055AFD84223DA52D38C4A691A785567E07".toLowerCase())) {
            return HttpStatus.BAD_REQUEST;
        }
        parkingSpotService.setParkingSpotFree(parkingSpotId);

        return HttpStatus.OK;


    }

//    @GetMapping(path="/admin/makeunavailable/{parkingSpotId}")
//    @ResponseBody
//    public HttpStatus getAllHistory(HttpServletRequest request, @PathVariable("parkingSpotId") Integer parkingSpotId) {
//        if(request.getCookies()==null || request.getCookies().length==0){
//            return HttpStatus.FORBIDDEN;
//        }
//        Cookie cookie = request.getCookies()[0];
//        if(!cookie.getValue().equals("AC75F6232AA3C5BE99ED53BADAD3C0055AFD84223DA52D38C4A691A785567E07".toLowerCase())){
//            return HttpStatus.BAD_REQUEST;
//        }
//        parkingSpotService.setParkingSpotUnavailable(parkingSpotId);
//
//        return HttpStatus.OK;
//
//    }

    @RequestMapping(value = "/setheaders", method = RequestMethod.GET)
    @ResponseBody
    public String setHeaders(HttpServletRequest request, HttpServletResponse response) {

        request.getCookies();

        Random rand = new Random();

        response.addCookie(new Cookie("foo", rand.nextInt(50) + ""));
        return "setHeaders";
    }
}









