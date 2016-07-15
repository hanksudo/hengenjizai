import logging
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

logger.info('Logging start')


records = {'one': 188, 'two': 233}
logger.debug('Records %s', records)
logger.info('after logging')

logger.info('Finished logging')
