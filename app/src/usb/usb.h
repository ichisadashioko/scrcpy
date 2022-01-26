#ifndef SC_USB_H
#define SC_USB_H

#include "common.h"

#include <stdbool.h>
#include <libusb-1.0/libusb.h>

struct sc_usb {
    libusb_context *context;
    libusb_device_handle *handle;
};

bool
sc_usb_init(struct sc_usb *usb, const char *serial);

void
sc_usb_destroy(struct sc_usb *usb);

#endif