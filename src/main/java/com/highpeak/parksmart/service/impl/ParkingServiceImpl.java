package com.highpeak.parksmart.service.impl;

import com.highpeak.parksmart.datastore.model.ParkingModel;
import com.highpeak.parksmart.datastore.model.SlotModel;
import com.highpeak.parksmart.datastore.model.UserModel;
import com.highpeak.parksmart.datastore.repository.ParkingRepository;
import com.highpeak.parksmart.datastore.repository.SlotRepository;
import com.highpeak.parksmart.datastore.model.UserModel;
import com.highpeak.parksmart.datastore.repository.ParkingRepository;
import com.highpeak.parksmart.datastore.repository.UserRepository;
import com.highpeak.parksmart.datastore.repository.VehicleRepository;
import com.highpeak.parksmart.datastore.repository.UserRepository;
import com.highpeak.parksmart.datastore.repository.VehicleRepository;
import com.highpeak.parksmart.exception.DataException;
import com.highpeak.parksmart.pojo.ParkingBean;
import com.highpeak.parksmart.pojo.SlotBean;
import com.highpeak.parksmart.service.ParkingService;
import com.highpeak.parksmart.util.Constants;
import com.highpeak.parksmart.util.MessageBundleResource;
import com.highpeak.parksmart.util.NullEmptyUtils;
import com.highpeak.parksmart.util.ValidationHelper;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    ParkingRepository parkingRepository;

    @Autowired
    SlotRepository slotRepository;

    @Autowired
    MessageBundleResource messageBundle;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;


    /**
     * Service to start parking
     *
     * @param parkingBean
     * @return
     */
    @Override
    public ParkingBean startParking(ParkingBean parkingBean) throws DataException
    {
        if(NullEmptyUtils.isNullorEmpty(parkingBean.getSlotId()) || NullEmptyUtils.isNullorEmpty(parkingBean.getVehicleId()))
        {
            throw new DataException(Constants.EXCEPTION,messageBundle.getMessage(Constants.EMPTY_FIELD), HttpStatus.BAD_REQUEST);
        }

        Optional<SlotModel> slotModel = slotRepository.findBySlotIdAndSlotActiveTrue(parkingBean.getSlotId());

        if(slotModel.isPresent())
        {
            throw new DataException(Constants.EXCEPTION,messageBundle.getMessage(Constants.SLOT_UNAVAILABLE),HttpStatus.BAD_REQUEST);
        }

        ParkingModel parkingModel = new ParkingModel();

        parkingModel.setSlotId(parkingBean.getSlotId());
        parkingModel.setVehicleId(parkingBean.getVehicleId());
        parkingModel.setStartTime(new Timestamp(System.currentTimeMillis()));
        parkingModel.setParkingIsActive(true);

        return setParkingBean(parkingRepository.save(parkingModel));
    }

    /**
     * Service to stop parking
     *
     * @param parkingBean
     * @return
     */

    @Override
    public ParkingBean stopParking(ParkingBean parkingBean, int userId) throws DataException
    {
        {
            if (NullEmptyUtils.isNullorEmpty(parkingBean.getParkingId()))
            {
                throw new DataException(Constants.EXCEPTION, messageBundle.getMessage(Constants.EMPTY_FIELD), HttpStatus.BAD_REQUEST);
            }
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
    }

    private float calculateAmount(Timestamp startTiome, Timestamp endTime){

        DateTime start = new DateTime(startTiome);
        DateTime end = new DateTime(endTime);

        int minutes = Minutes.minutesBetween(start, end).getMinutes();
        float chargePerMin = 0.50f;
        float price = minutes * chargePerMin;
        return price;
    }

    /**
     * Service to find car
     *
     * @param parkingBean
     * @return
     */


    @Override
    public SlotBean findCar(ParkingBean parkingBean) throws DataException
    {
        if(NullEmptyUtils.isNullorEmpty(parkingBean.getParkingId()))
        {
            throw new DataException(Constants.EXCEPTION,messageBundle.getMessage(Constants.EMPTY_FIELD), HttpStatus.BAD_REQUEST);
        }

        Optional<ParkingModel> parkingModel = parkingRepository.findByParkingIdAndParkingIsActiveTrue(parkingBean.getParkingId());

        if(!parkingModel.isPresent())
        {
            throw new DataException(Constants.EXCEPTION,messageBundle.getMessage(Constants.PARKING_DOESNT_EXIST),HttpStatus.BAD_REQUEST);
        }

        Optional<SlotModel> slotModel = slotRepository.findBySlotIdAndSlotActiveTrue(parkingModel.get().getSlotId());

        if(!slotModel.isPresent())
        {
            throw new DataException(Constants.EXCEPTION,messageBundle.getMessage(Constants.SLOT_DOESNT_EXIST),HttpStatus.BAD_REQUEST);
        }

        return setSlotBean(slotModel.get());

    }

    /**
     * private method to set slotBean
     *
     * @param slotModel
     * @return
     */

    private SlotBean setSlotBean(SlotModel slotModel)
    {
        SlotBean slotBean = new SlotBean();

        slotBean.setSlotLat(slotModel.getSlotLat());
        slotBean.setSlotLong(slotModel.getSlotLong());

        return slotBean;
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
        parkingBean.setStartTime(parkingModel.getStartTime().getTime());

        if(NullEmptyUtils.isNull(parkingModel.getEndTime()))
        {
            parkingBean.setEndTime(null);
        }

        parkingBean.setEndTime(parkingModel.getEndTime().getTime());
        parkingBean.setParkingIsActive(parkingModel.isParkingIsActive());
        parkingBean.setAmount(parkingModel.getAmount());

        return parkingBean;
    }
}
