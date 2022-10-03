package me.legrange.escl.discovery;

import net.straylightlabs.hola.dns.Domain;
import net.straylightlabs.hola.sd.Query;
import net.straylightlabs.hola.sd.Service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;

final class Discovery {

    public static void main(String[] args) throws DiscoveryException {
        Set<ScannerRecord> scanners = null;
        do {
            scanners = new Discovery().findScanners();

        } while (scanners.isEmpty());
        scanners.forEach(System.out::println);
    }

    Set<ScannerRecord> findScanners() throws DiscoveryException {
        var insecure = findScanners("_uscan._tcp").stream()
                .collect(Collectors.toMap(rec -> rec.getUuid(), rec -> rec));
        var secure = findScanners("_uscans._tcp").stream()
                .collect(Collectors.toMap(rec -> rec.getUuid(), rec -> rec));
        var res = new HashMap<String, ScannerRecord>();
        res.putAll(insecure);
        res.putAll(secure);
        return new HashSet<>(res.values());
    }

    Set<ScannerRecord> findScanners(String serviceType) throws DiscoveryException {
        try {
            var service = Service.fromName(serviceType);
            var query = Query.createFor(service, Domain.LOCAL);
            var instances = query.runOnce();
            var res = new HashSet<ScannerRecord>();
            for (var instance : instances) {
                var rec = new ScannerRecord(instance.lookupAttribute("UUID"),
                        instance.getAddresses().stream()
                                .map(addr -> addr.getHostAddress()).collect(Collectors.toList()),
                        instance.getPort());
                rec.setVersion(instance.lookupAttribute("txtvers"));
                rec.setAdminUrl(instance.lookupAttribute("adminurl"));
                rec.setColorSpace(Arrays.asList(instance.lookupAttribute("cs").split(",")));
                rec.setInputSource(Arrays.asList(instance.lookupAttribute("is").split(",")));
                rec.setRepresentation(instance.lookupAttribute("representation"));
                rec.setResource(instance.lookupAttribute("rs"));
                rec.setMakeModel(instance.lookupAttribute("ty"));
                rec.setNote(instance.lookupAttribute("note"));
                rec.setMimeTypes(Arrays.asList(instance.lookupAttribute("pdl").split(",")));
                rec.setDuplex(instance.lookupAttribute("duplex"));
                res.add(rec);
            }
            return res;
        } catch (UnknownHostException e) {
            throw new DiscoveryException(format("Unknown host (%s)", e.getMessage()), e);
        } catch (IOException e) {
            throw new DiscoveryException(format("IO error (%s)", e.getMessage()), e);
        }
    }

    private Discovery() {
    }

}