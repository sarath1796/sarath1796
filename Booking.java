class BookingSystem:
    def __init__(self):
        self.bookings = {}

    def create_booking(self, num_guests, date, time, duration, customer_id):
        if self.check_availability(date, time, duration):
            booking_id = len(self.bookings) + 1
            self.bookings[booking_id] = {
                'num_guests': num_guests,
                'date': date,
                'time': time,
                'duration': duration,
                'customer_id': customer_id,
                'status': 'pending'
            }
            return f"Booking created successfully. Your booking ID is {booking_id}."
        else:
            return "Selected time slot is not available."

    def cancel_booking(self, booking_id):
        if booking_id in self.bookings:
            self.bookings[booking_id]['status'] = 'cancelled'
            return "Booking cancelled successfully."
        else:
            return "Booking ID not found."

    def check_availability(self, date, time, duration):
        for booking in self.bookings.values():
            if booking['date'] == date:
                start_time = self.get_datetime(booking['time'])
                end_time = start_time + duration
                new_start_time = self.get_datetime(time)
                new_end_time = new_start_time + duration
                if start_time <= new_start_time < end_time or start_time < new_end_time <= end_time:
                    return False
        return True

    def get_datetime(self, time_str):
        return int(time_str.split(':')[0]) * 60 + int(time_str.split(':')[1])


