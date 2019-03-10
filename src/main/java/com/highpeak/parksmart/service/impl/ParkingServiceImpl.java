package com.highpeak.parksmart.service.impl;

import com.highpeak.parksmart.datastore.model.ParkingModel;
import com.highpeak.parksmart.datastore.model.UserModel;
import com.highpeak.parksmart.datastore.repository.ParkingRepository;
import com.highpeak.parksmart.datastore.repository.UserRepository;
import com.highpeak.parksmart.datastore.repository.VehicleRepository;
import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.ParkingBean;
import com.highpeak.parksmart.service.ParkingService;
import com.highpeak.parksmart.util.Constants;
import com.highpeak.parksmart.util.ValidationHelper;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingRepository parkingRepository;


    @Override
    public ParkingBean startParking(ParkingBean parkingBean, int userId) throws DataException {
        Optional<UserModel> optionalUserModel = userRepository.findByIdAndIsActiveTrue(userId);
        ValidationHelper.checkUserById(optionalUserModel);

        ParkingModel parkingModel = new ParkingModel();
        parkingModel.setStartTime(new Timestamp(System.currentTimeMillis()));
        parkingModel.setVehicleId(vehicleRepository.findByUserIdAndIsActiveTrue(optionalUserModel.get().getId()).get().getId());
        parkingModel.setSlotId(1);
        parkingModel.setParkingIsActive(true);
        return setParkingBean(parkingModel);
    }

    @Override
    public ParkingBean stopParking(ParkingBean parkingBean, int userId) throws DataException {
        Optional<UserModel> optionalUserModel = userRepository.findByIdAndIsActiveTrue(userId);
        ValidationHelper.checkUserById(optionalUserModel);

        Optional<ParkingModel> parkingModelOptional = parkingRepository.findByVehicleIdAndParkingIsActiveTrue(parkingBean.getVehicleId());
        if (!parkingModelOptional.isPresent())
            throw new DataException(Constants.EXCEPTION, "Booking not found", HttpStatus.BAD_REQUEST);

        ParkingModel parkingModel = parkingModelOptional.get();
        parkingModel.setEndTime(new Timestamp(System.currentTimeMillis()));
        parkingModel.setAmount(String.valueOf(calculateAmount(parkingModel.getStartTime(), parkingModel.getEndTime())));
        parkingModel.setParkingIsActive(false);
        return setParkingBean(parkingModel);
    }

    private float calculateAmount(Timestamp startTiome, Timestamp endTime){

        DateTime start = new DateTime(startTiome);
        DateTime end = new DateTime(endTime);

        int minutes = Minutes.minutesBetween(start, end).getMinutes();
        float chargePerMin = 0.50f;
        float price = minutes * chargePerMin;
        return price;
    }

    @Override
    public ParkingBean findCar(ParkingBean parkingBean, int userId) throws DataException {
        return null;
    }

    /**
     * private method to set parkingBean
     *
     * @param parkingModel
     * @return
     */

    private ParkingBean setParkingBean(ParkingModel parkingModel)
    {
        ParkingBean parkingBean = new ParkingBean();

        parkingBean.setParkingId(parkingModel.getParkingId());
        parkingBean.setVehicleId(parkingModel.getVehicleId());
        parkingBean.setSlotId(parkingModel.getSlotId());
        parkingBean.setStartTime(parkingModel.getStartTime());
        parkingBean.setEndTime(parkingModel.getEndTime());
        parkingBean.setAmount(parkingModel.getAmount());

        return parkingBean;
    }
}
